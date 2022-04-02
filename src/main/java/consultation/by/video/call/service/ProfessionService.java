package consultation.by.video.call.service;

import consultation.by.video.call.model.request.ProfessionRequest;
import consultation.by.video.call.model.response.ProfessionResponse;

import java.util.List;

public interface ProfessionService {

    ProfessionResponse saveProfession(ProfessionRequest request);

    List<ProfessionResponse> getProfessions();

    void updateProfession(Long id, ProfessionRequest request);

    void deleteProfession(Long id);
}
