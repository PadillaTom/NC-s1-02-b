package consultation.by.video.call.service;

import consultation.by.video.call.model.response.PatientResponseById;
import consultation.by.video.call.model.response.PatientsReponse;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientTurnResponse;
import java.util.List;

public interface  PatientService {
   
   PatientTurnResponse savePatientTurn(PatientTurnRequest request);
   List<PatientsReponse> getPatients();

    PatientResponseById getPatientById(Long id);
}
