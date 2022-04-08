package consultation.by.video.call.auth.service;


import consultation.by.video.call.auth.entity.ListRole;
import consultation.by.video.call.model.entity.Role;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.auth.service.abstraction.IAuthenticationService;
import consultation.by.video.call.auth.service.abstraction.IUserService;
import consultation.by.video.call.auth.service.abstraction.IRegisterUserService;
import consultation.by.video.call.auth.mapper.UserMapper;
import consultation.by.video.call.auth.repository.IUserRepository;
import consultation.by.video.call.auth.request.RolesRequest;
import consultation.by.video.call.auth.request.UserAuthenticatedRequest;
import consultation.by.video.call.auth.request.UserRegisterRequest;
import consultation.by.video.call.auth.request.UserRequest;
import consultation.by.video.call.auth.response.RoleResponse;
import consultation.by.video.call.auth.response.UserAuthenticatedResponse;
import consultation.by.video.call.auth.response.UserRegisterResponse;
import consultation.by.video.call.auth.response.UserResponse;
import consultation.by.video.call.auth.response.UserRoleResponse;
import consultation.by.video.call.auth.service.abstraction.IRoleService;
import consultation.by.video.call.exception.ParamNotFound;
import consultation.by.video.call.model.entity.Patient;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl  implements UserDetailsService, IRegisterUserService, IAuthenticationService, IUserService  {
//   
    private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
    private static final String USER_EMAIL_ERROR = "Email address is already used.";
    private static final String USER_LIST_ERROR = "Empty user list";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request, MultipartFile[] file) {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new RuntimeException(USER_EMAIL_ERROR);
        }
        Patient user = (Patient) userMapper.userDto2Entity(request, file);  
        List<Role> roles = new ArrayList<>();
        //USER-PATIENT
        roles.add(roleService.findBy(ListRole.PATIENT.getFullRoleName()));
        user.setRoles(roles);         
        Patient userCreate = userRepository.save(user);
        UserRegisterResponse userRegisterResponse = userMapper.userEntity2Dto(userCreate);
        userRegisterResponse.setToken(jwtUtil.generateTokenPatient((UserDetails) userCreate));
        return userRegisterResponse;      
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) getUser(email);
    }

    private User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return userOptional.get();
    }
//
    private User getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
        }        
        return user;
    }

    @Override
    public UserAuthenticatedResponse authentication(UserAuthenticatedRequest request) {
       
        User user = getUser(request.getEmail());       
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        return new UserAuthenticatedResponse(jwtUtil.generateToken(user), user.getEmail(), user.getAuthorities());
    }


    @Override
    public User getInfoUser() throws NotFoundException {
        Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userInstance instanceof User){
            String username = ((User) userInstance).getUsername();//            
        }else{
            String username = userInstance.toString();
      }
        System.out.println("EL USSS ES: "+ userInstance.toString());
        return userRepository.findByEmail(userInstance.toString());
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        User user = getUser(id);
        user.setDeleted(true);
       //auditoria
        userRepository.save(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) throws NotFoundException {
        Optional<User> entity = userRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("error: id Username is not valido");
        }       
        User entitySaved = userRepository.save(userMapper.userDtoEntity(entity.get(), request));
        return userMapper.convertTo(entitySaved);
    }

    
    @Override
    public UserRoleResponse updateRoles(Long id, List<Role> roleNames){
          Optional<User> entity = userRepository.findById(id);        
          if(!entity.isPresent()){
            throw new ParamNotFound("error: id Username is not valido");
          }          
        List<Role> rolesNew = new ArrayList<>();   
        for (Role role :roleNames){       
             rolesNew.add(roleService.findById(role.getId()));           
        }
           entity.get().setRoles(rolesNew);                    
          return userMapper.convertToUserRole(userRepository.save(entity.get()));
    }
    
    
    
    @Override
    public UserResponse getById(Long id) {
        User user= getUser(id);
        return userMapper.convertTo(user);
    }
    
    @Transactional
    @Override
    public List<UserResponse> getAllUser() {
       return listAllUser(userRepository.findAll());
        
    }

    
    public List<UserResponse> listAllUser(List<User> entities) {
        List<UserResponse> listResponse = new ArrayList<>();
        if (entities.size() == 0) {
            throw new EntityNotFoundException(USER_LIST_ERROR);
        }
        for (User entity : entities) {
            listResponse.add(userMapper.convertTo(entity));
        }
        return listResponse;
    }
}
