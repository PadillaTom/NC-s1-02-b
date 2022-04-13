package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Turn;
import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import org.springframework.stereotype.Component;

@Component
public class TurnMapper {
   
    public Turn toDTO(PatientTurnResponse entity, Professional professional, Patient patient) {
        
        return Turn.builder()
                .day(entity.getDayMonthYear())
                .hour(entity.getHomework())
                .high(EnumState.ACTIVED)                                
                .professional(professional)
                .patient(patient)
                .build();
    }
    
    public TurnsPatientResponse toDtResponse(Turn turns){
        return TurnsPatientResponse.builder()
                .id(turns.getId())
                .dayMonthYear(turns.getDay())
                .homework(turns.getHour())
                .status(turns.getHigh())               
                .patient(turns.getPatient().getId())
                .professional_id(turns.getProfessional().getId())
                .build();
    }
    

}
