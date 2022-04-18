package consultation.by.video.call.service;


import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.model.request.ProfessionalAuthenticatedRequest;
import consultation.by.video.call.model.request.ProfessionalRequest;
import consultation.by.video.call.model.response.ProfessionalAuthenticatedResponse;
import consultation.by.video.call.model.response.ProfessionalListResponse;
import consultation.by.video.call.model.response.ProfessionalResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProfessionalService {
    ProfessionalResponse registerProfessional(ProfessionalRequest request, MultipartFile[] file);

    ProfessionalAuthenticatedResponse authentication(ProfessionalAuthenticatedRequest request);

    List<ProfessionalListResponse> getAllProfessionals();

    List<ProfessionalListResponse> getProfessionalByProfessionId(Long professionId);

    List<ProfessionalListResponse> getByFilters(String email, String first_name, String last_name, String dni);

    void deleted(Long id);

    ProfessionalListResponse getById(Long id);


}
