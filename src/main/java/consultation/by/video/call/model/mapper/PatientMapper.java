package consultation.by.video.call.model.mapper;


import consultation.by.video.call.auth.response.PatientsReponse;
import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientTurnResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

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
}
