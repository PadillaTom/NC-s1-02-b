package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.model.request.UserRegisterRequest;
import consultation.by.video.call.model.request.UserRequest;
import consultation.by.video.call.model.response.*;
import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FirebaseService service;
    @Autowired
    private TurnMapper turnMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;

    public Patient userDto2Entity(UserRegisterRequest request, MultipartFile[] file) {
        Patient user = new Patient();
        String imageUrl = service.subirImagen(file);
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setDni(request.getDni());
        user.setFirstName(request.getFirst_name());
        user.setLastName(request.getLast_name());
        user.setImageUrl(imageUrl);
        user.setProvince(request.getProvince());
        user.setRoles(null);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public UserRegisterResponse userEntity2Dto(Patient user) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setFirstName(user.getFirstName());
        userRegisterResponse.setLastName(user.getLastName());
        userRegisterResponse.setEmail(user.getEmail());
        userRegisterResponse.setRoles(user.getRoles());

        return userRegisterResponse;
    }

    //
    public User userDtoEntity(User entity, UserRequest request) {
        entity.setAge(request.getAge());
        entity.setEmail(request.getEmail());
        entity.setAge(request.getAge());
        entity.setCity(request.getCity());
        entity.setCountry(request.getCountry());
        entity.setDni(request.getDni());
        entity.setFirstName(request.getFirst_name());
        entity.setLastName(request.getLast_name());
        entity.setImageUrl(request.getImage_url());
        entity.setProvince(request.getProvince());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        return entity;

    }


    public UserResponse convertTo(User userInstance, boolean loadTurns, boolean loadSchedule) {
        UserResponse userResponse = new UserResponse();
            if (userInstance instanceof Professional) {
                userResponse.setId(userInstance.getId());
                userResponse.setFirt_name(userInstance.getFirstName());
                userResponse.setAge(userInstance.getAge());
                userResponse.setDni(userInstance.getDni());
                userResponse.setLast_name(userInstance.getLastName());
                userResponse.setUsername(userInstance.getUsername());
                userResponse.setCity(userInstance.getCity());
                userResponse.setCountry(userInstance.getCountry());
                userResponse.setEmail(userInstance.getEmail());
                userResponse.setPassword(userInstance.getPassword());
                userResponse.setImage_url(userInstance.getImageUrl());
                userResponse.setProvince(userInstance.getProvince());
                userResponse.setRoles(userInstance.getRoles());
                userResponse.setEnrollment(((Professional) userInstance).getEnrollment());
                userResponse.setConsultationPrice(((Professional) userInstance).getConsultationPrice());

                if(loadTurns){
                    List<TurnsPatientResponse> turns = turnMapper.turnEntitySet2DtoList(((Professional) userInstance).getTurnList());
                    userResponse.setTurns(turns);
                }
                if(loadSchedule){
                    List<ScheduleResponse> schedules = scheduleMapper.scheduleEntity2DtoList(((Professional) userInstance).getSchedule());
                    userResponse.setSchedules(schedules);
                }
            }

            if(userInstance instanceof Patient){
                userResponse.setId(userInstance.getId());
                userResponse.setFirt_name(userInstance.getFirstName());
                userResponse.setAge(userInstance.getAge());
                userResponse.setDni(userInstance.getDni());
                userResponse.setLast_name(userInstance.getLastName());
                userResponse.setUsername(userInstance.getUsername());
                userResponse.setCity(userInstance.getCity());
                userResponse.setCountry(userInstance.getCountry());
                userResponse.setEmail(userInstance.getEmail());
                userResponse.setPassword(userInstance.getPassword());
                userResponse.setImage_url(userInstance.getImageUrl());
                userResponse.setProvince(userInstance.getProvince());
                userResponse.setRoles(userInstance.getRoles());

                if(loadTurns){
                    List<TurnsPatientResponse> turns = turnMapper.turnEntitySet2DtoList(((Patient) userInstance).getTurnList());
                    userResponse.setTurns(turns);
                }
            }

        return userResponse;
    }

    
    public UserRoleResponse convertToUserRole(User user) {
        UserRoleResponse userResponse = new UserRoleResponse();
        userResponse.setId(user.getId());
        userResponse.setFirt_name(user.getFirstName());
      //  userResponse.setRoles(user.getRoles());
        return userResponse;
    }
    
    
}
