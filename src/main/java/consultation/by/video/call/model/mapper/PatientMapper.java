package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientResponseById;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.model.response.PatientsReponse;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PatientMapper {
    @Autowired
    private TurnMapper turnMapper;

    public PatientTurnResponse toEntity(PatientTurnRequest dto, String dayName) {
            return PatientTurnResponse.builder()
                .consultationPrice(dto.getConsultationPrice())
                .lastName(dto.getProfessional().getLastName())
                .firstName(dto.getProfessional().getFirstName())
                .professional_id(dto.getProfessional().getId())
                .dayMonthYear(dto.getDayMonthYear())
                .homework(dto.getHomework())
                .weekday(dayName)
                .status(EnumState.ACTIVED)
                .build();
        
    }

    public PatientsReponse toDTO(Patient entity) {
        return PatientsReponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .dni(entity.getDni())                
                .firt_name(entity.getFirstName())
                .last_name(entity.getLastName())
                .image_url(entity.getImageUrl())
                .build();
    }

    public PatientResponseById toDTOPatient(Patient patient, boolean loadTurns) {
        PatientResponseById response = new PatientResponseById();
        response.setId(patient.getId());
        response.setDni(patient.getDni());
        response.setFirt_name(patient.getFirstName());
        response.setImage_url(patient.getImageUrl());
        response.setLast_name(patient.getLastName());
        response.setUsername(patient.getUsername());
        if(loadTurns){
            List<TurnsPatientResponse> turns = turnMapper.turnEntitySet2DtoList(patient.getTurnList());
            response.setTurns(turns);
        }
        return response;
    }
}
