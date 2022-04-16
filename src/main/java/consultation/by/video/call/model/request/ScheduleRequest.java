
package consultation.by.video.call.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class ScheduleRequest {
    

    @JsonFormat(pattern="dd/MM/yyyy")    
    private LocalDate day; 
    private List<LocalTime> hours;
   
    @Enumerated(value = EnumType.STRING)
    private EnumState status=EnumState.ACTIVED;
}
