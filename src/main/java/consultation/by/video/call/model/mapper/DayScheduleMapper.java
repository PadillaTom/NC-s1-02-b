
package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.DaySchedule;
import consultation.by.video.call.model.entity.Schedule;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DayScheduleMapper {
    
    public DaySchedule EntityTo(List<DaySchedule> daySchedule, LocalTime request, Schedule s){
        return DaySchedule.builder()
                .hoursWork(request)
                .schedule(s)
                .status(EnumState.ACTIVED)
                .build();
    }
}
