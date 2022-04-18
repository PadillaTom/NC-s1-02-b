package consultation.by.video.call.model.response;


import consultation.by.video.call.model.entity.DaySchedule;
import consultation.by.video.call.model.entity.Schedule;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
    
    private Long id_professional;
    private String firstName;
    private String lastName;    
    private List<DayScheduleResponse> daySchedule;
        
   //daySchedule
//    private Long id_dayschedule;
//    private LocalDate dayMonthYear;//dia del mes   
//    private LocalTime homework; // Inicio del horario laboral de cada profesional
//    private EnumState status;  
}
