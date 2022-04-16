package consultation.by.video.call.service.impl;

import consultation.by.video.call.model.enums.ListRole;
import consultation.by.video.call.filter.JwtUtil;
import consultation.by.video.call.service.IRoleService;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Role;
import consultation.by.video.call.model.mapper.ProfessionalMapper;
import consultation.by.video.call.model.request.ProfessionalAuthenticatedRequest;
import consultation.by.video.call.model.request.ProfessionalFiltersRequest;
import consultation.by.video.call.model.request.ProfessionalRequest;
import consultation.by.video.call.model.response.ProfessionalAuthenticatedResponse;
import consultation.by.video.call.model.response.ProfessionalListResponse;
import consultation.by.video.call.model.response.ProfessionalResponse;
import consultation.by.video.call.repository.ProfessionalRepository;
import consultation.by.video.call.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    @Autowired
    private ProfessionalMapper professionalMapper;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ProfessionalResponse registerProfessional(ProfessionalRequest request, MultipartFile[] file) {
        if(professionalRepository.findByEmail(request.getEmail()) != null){
            throw new RuntimeException("El email ya se encuentra registrado.");
        }
        Professional professional = professionalMapper.toEntity(request, file);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findBy(ListRole.PROFESSIONAL.getFullRoleName()));
        professional.setRoles(roles);
        Professional saved = professionalRepository.save(professional);
        ProfessionalResponse response = professionalMapper.toDto(saved);
        response.setToken(jwtUtil.generateToken(saved));
        return response;
    }

    @Override
    public ProfessionalAuthenticatedResponse authentication(ProfessionalAuthenticatedRequest request) {
        Professional professional = getProfessional(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        return new ProfessionalAuthenticatedResponse(jwtUtil.generateToken(professional), professional.getEmail(), professional.getAuthorities());
    }

    @Override
    public List<ProfessionalListResponse> getAllProfessionals() {
        List<Professional> professionals = professionalRepository.findAll();
        List<ProfessionalListResponse> response = professionalMapper.toDtoList(professionals);
        return response;
    }

    @Override
    public List<ProfessionalListResponse> getProfessionalByProfessionId(Long professionId) {
        List<Professional> professionals = professionalRepository.findAll();
        List<ProfessionalListResponse> result = professionalMapper.toDtoListById(professionals, professionId);
        return result;

    }

    @Override
    public List<ProfessionalListResponse> getByFilters(String email, String first_name, String last_name, String dni) {
        ProfessionalFiltersRequest filtersDto = new ProfessionalFiltersRequest(email,first_name,last_name,dni);
        List<ProfessionalListResponse> response = new ArrayList<>();
        List<Professional> professionals = professionalRepository.findAll();
           for (Professional p: professionals){
               if(p.getEmail().equals(filtersDto.getEmail()) || p.getFirstName().equals(filtersDto.getFirst_name()) ||
               p.getLastName().equals(filtersDto.getLast_name()) || p.getDni().equals(filtersDto.getDni())){
                   response.add(professionalMapper.professionalEntityBasicDto(p));
               }
           }

        return response;
    }

    @Override
    public void deleted(Long id) throws EntityNotFoundException {
        Professional p = getProfessional(id);
        p.setDeleted(true);
        professionalRepository.save(p);
    }

    @Override
    public ProfessionalListResponse getById(Long id) {
        Professional professional = getProfessional(id);
        ProfessionalListResponse response = professionalMapper.professionalEntityBasicDto(professional);
        return response;
    }

    private Professional getProfessional(Long id){
        Optional<Professional> professional = professionalRepository.findById(id);
        if(professional.isEmpty() || professional.get().isDeleted()){
            throw new RuntimeException("professional no registrado");
        }
        return professional.get();
    }

    private Professional getProfessional(String email) {
        Professional professional = professionalRepository.findByEmail(email);
        if(professional == null){
            throw new RuntimeException("Professional no registrado");
        }
        return professional;
    }
}
