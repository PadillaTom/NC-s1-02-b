package consultation.by.video.call.model.response;

import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayScheduleResponse {
   
    private Long id;      
    private LocalDate dayMonthYear;//dia del mes
    private List<LocalTime> homework; // Inicio del horario laboral de cada profesional
    private EnumState status;


}
