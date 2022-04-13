package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Person;
import consultation.by.video.call.model.entity.Profession;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.model.request.ProfessionalRequest;
import consultation.by.video.call.model.response.ProfessionalListResponse;
import consultation.by.video.call.model.response.ProfessionalResponse;
import consultation.by.video.call.service.FirebaseService;
import consultation.by.video.call.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProfessionalMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FirebaseService service;
    @Autowired
    private ProfessionService professionService;

    public Professional toEntity(ProfessionalRequest request, MultipartFile[] file) {
        String imageUrl = service.subirImagen(file);
        Professional p = new Professional();
        p.setEmail(request.getEmail());
        p.setAge(request.getAge());
        p.setCity(request.getCity());
        p.setCountry(request.getCountry());
        p.setDni(request.getDni());
        p.setFirstName(request.getFirst_name());
        p.setLastName(request.getLast_name());
        p.setImageUrl(imageUrl);
        p.setProvince(request.getProvince());
        p.setPassword(passwordEncoder.encode(request.getPassword()));
        p.setConsultationPrice(request.getConsultationPrice());
        p.setEnrollment(request.getEnrollment());
        Profession profession = professionService.getProfession(request.getProfessionId());
        p.setProfessions(profession);
        return p;
    }

    public ProfessionalResponse toDto(Professional saved) {
        ProfessionalResponse response= new ProfessionalResponse();
        response.setEmail(saved.getEmail());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setId(saved.getId());
        response.setImageUrl(saved.getImageUrl());
        return response;
    }

    public List<ProfessionalListResponse> toDtoList(List<Professional> professionals) {
        List<ProfessionalListResponse> responses = new ArrayList<>();
        ProfessionalListResponse response;
        for(Professional p: professionals){
            response = new ProfessionalListResponse();
            response.setId(p.getId());
            response.setAge(p.getAge());
            response.setCity(p.getCity());
            response.setEmail(p.getEmail());
            response.setDni(p.getDni());
            response.setCountry(p.getCountry());
            response.setEnrollment(p.getEnrollment());
            response.setConsultationPrice(p.getConsultationPrice());
            response.setFirst_name(p.getFirstName());
            response.setImage_url(p.getImageUrl());
            response.setProvince(p.getProvince());
            response.setLast_name(p.getLastName());
            response.setPassword(p.getPassword());
            response.setProfession(p.getProfessions());
            responses.add(response);
        }
        return responses;
    }

    public List<ProfessionalListResponse> toDtoListById(List<Professional> professionals, Long professionId) {
        List<ProfessionalListResponse> responses = new ArrayList<>();
        for(Professional p: professionals){

            if(Objects.equals(p.getProfessions().getId(), professionId)){
                responses.add(professionalEntityBasicDto(p));
            }

        }
        return responses;


    }

    public ProfessionalListResponse professionalEntityBasicDto(Professional p){
        ProfessionalListResponse response = new ProfessionalListResponse();
        response.setId(p.getId());
        response.setAge(p.getAge());
        response.setCity(p.getCity());
        response.setEmail(p.getEmail());
        response.setDni(p.getDni());
        response.setCountry(p.getCountry());
        response.setEnrollment(p.getEnrollment());
        response.setConsultationPrice(p.getConsultationPrice());
        response.setFirst_name(p.getFirstName());
        response.setImage_url(p.getImageUrl());
        response.setProvince(p.getProvince());
        response.setLast_name(p.getLastName());
        return  response;
    }
}
