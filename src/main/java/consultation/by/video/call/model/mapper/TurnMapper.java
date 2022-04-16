package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Turn;
import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public List<TurnsPatientResponse> turnEntitySet2DtoList(Collection<Turn> turns) {
        List<TurnsPatientResponse> dtos = new ArrayList<>();
        for(Turn t: turns){
            dtos.add(this.turnEntity2DTO(t));
        }
        return dtos;
    }

    private TurnsPatientResponse turnEntity2DTO(Turn t) {
        TurnsPatientResponse dto = new TurnsPatientResponse();
        dto.setId(t.getId());
        dto.setHomework(t.getHour());
        dto.setDayMonthYear(t.getDay());
        dto.setPatient(t.getPatient().getId());
        dto.setProfessional_id(t.getProfessional().getId());
        dto.setStatus(t.getHigh());
        return dto;
    }
}
