
package consultation.by.video.call.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.entity.Schedule;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayScheduleRequest {

    @JsonFormat(pattern="HH:mm")
    @NotNull(message = "You must enter a valid hour") 
    private LocalTime hoursWork;
    @Enumerated(value = EnumType.STRING)
    private EnumState status;
    
    @ManyToOne
//    @JoinColumn(name = "fk_schedule", updatable = false, nullable = false)
    private Schedule schedule;
}
