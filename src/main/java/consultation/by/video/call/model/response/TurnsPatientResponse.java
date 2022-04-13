
package consultation.by.video.call.model.response;


import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TurnsPatientResponse {   
    private Long id ;
    private LocalDate dayMonthYear;//dia del mes  
    private LocalTime homework; // Inicio del horario laboral de cada profesional    
    private EnumState status;   
    private Long patient;
    //professional
    private Long professional_id;

    
}
