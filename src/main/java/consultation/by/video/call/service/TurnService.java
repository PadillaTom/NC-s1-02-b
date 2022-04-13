package consultation.by.video.call.service;

import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import java.util.List;


public interface  TurnService {
   TurnsPatientResponse getTurnById(Long Id);
   List<TurnsPatientResponse> getAllTurns();
   List<TurnsPatientResponse> getAllTurnsActived(EnumState high);
}
