
package consultation.by.video.call.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PatientTurnRequest {
    //professional  
    @JsonFormat(pattern="dd/MM/yyyy")    
    private LocalDate dayMonthYear;//dia del mes
    @JsonFormat(pattern="HH:mm")
    @NotNull(message = "You must enter a valid hour")
    private LocalTime homework; // Inicio del horario laboral de cada profesional    
    @Enumerated(value = EnumType.STRING)
    private Enum status=EnumState.ACTIVED;
    private double consultationPrice; 
    private Professional professional;
   
    
  
}
