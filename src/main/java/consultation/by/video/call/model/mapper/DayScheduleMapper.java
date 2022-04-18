
package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.DaySchedule;
import consultation.by.video.call.model.entity.Schedule;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import consultation.by.video.call.model.response.DayScheduleResponse;
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

    public List<DayScheduleResponse> dayScheduleEntity2DtoList(Collection<DaySchedule> homeWork) {
        List<DayScheduleResponse> day = new ArrayList<>();
        for (DaySchedule d: homeWork){
            day.add(this.dayScheduleEntity2DTO(d));
        }
        return day;
    }

    private DayScheduleResponse dayScheduleEntity2DTO(DaySchedule d) {
        DayScheduleResponse daySchedules = new DayScheduleResponse();

        daySchedules.setId(d.getId());
        daySchedules.setDayMonthYear(d.getSchedule().getDayMonthYear());
        List<LocalTime> localTimes = Collections.singletonList(d.getHoursWork());
        daySchedules.setHomework(localTimes);
        daySchedules.setStatus(d.getStatus());
        daySchedules.setDayMonthYear(d.getSchedule().getDayMonthYear());
        return daySchedules;

    }
}
