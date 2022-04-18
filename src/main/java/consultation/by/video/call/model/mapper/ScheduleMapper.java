
package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.DaySchedule;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Schedule;
import consultation.by.video.call.model.request.ScheduleRequest;
import consultation.by.video.call.model.response.DayScheduleResponse;
import consultation.by.video.call.model.response.ScheduleResponse;
import consultation.by.video.call.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
 
    @Autowired 
    private ScheduleRepository  scheduleRepository;
    @Autowired
    private DayScheduleMapper dayScheduleMapper;
    
    public ScheduleResponse toEntity(ScheduleRequest request, Professional professional){
        return ScheduleResponse.builder()
                .id_professional(professional.getId())
                .firstName(professional.getFirstName())
                .lastName(professional.getLastName())
//                .daySchedule(request.getDaySchedule())                
                .build();
    }
   public Schedule toEntitySchedule(ScheduleRequest request, Professional professional, List<DaySchedule> daySchedule){
        return Schedule.builder()
              .dayMonthYear(request.getDay())
              .professional(professional)
              .homeWork(daySchedule)    
                .build();
    }

    public List<ScheduleResponse> scheduleEntity2DtoList(Collection<Schedule> schedules) {
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();
        for(Schedule s: schedules){
            scheduleResponses.add(scheduleEntity2Dto(s, true));
        }
        return scheduleResponses;
    }

    private ScheduleResponse scheduleEntity2Dto(Schedule s, boolean loadDaySchedule) {
        ScheduleResponse response = new ScheduleResponse();
        response.setId_professional(s.getProfessional().getId());
        response.setLastName(s.getProfessional().getLastName());
        response.setFirstName(s.getProfessional().getFirstName());

        if(loadDaySchedule){
            List<DayScheduleResponse> daySchedules = dayScheduleMapper.dayScheduleEntity2DtoList(s.getHomeWork());
            response.setDaySchedule(daySchedules);
        }
        return response;
    }
}
