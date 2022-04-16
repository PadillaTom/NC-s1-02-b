package consultation.by.video.call.service.impl;

import consultation.by.video.call.exception.ParamNotFound;
import consultation.by.video.call.model.entity.DaySchedule;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Schedule;
import consultation.by.video.call.model.mapper.DayScheduleMapper;
import consultation.by.video.call.model.mapper.ScheduleMapper;
import consultation.by.video.call.model.request.ScheduleRequest;
import consultation.by.video.call.model.response.ScheduleResponse;
import consultation.by.video.call.repository.DayScheduleRepository;
import consultation.by.video.call.repository.ScheduleRepository;
import consultation.by.video.call.service.IUserService;
import consultation.by.video.call.service.ScheduleService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javassist.NotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private IUserService userService;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired 
    private DayScheduleRepository dayScheduleRepository;
    
     @Autowired 
    private ScheduleRepository scheduleRepository;
     @Autowired
     private DayScheduleMapper dayScheduleMapper;
     
    @Override
    @Transactional
    public ScheduleResponse save(ScheduleRequest request) {
       Professional professional=userProfessional();        
       List<DaySchedule> daySchedule=new ArrayList<>();  
       Schedule s=scheduleRepository.save(scheduleMapper.toEntitySchedule(request, professional, daySchedule));   
       
       daySchedule=formDaySchedule(daySchedule, request, s);
      
       ScheduleResponse reponse= scheduleMapper.toEntity(request, professional);
       return reponse;
    }

    
    @Transactional
    private List<DaySchedule> formDaySchedule(List<DaySchedule> daySchedule, ScheduleRequest request, Schedule s) {       
        for (int i = 0; i < request.getHours().size() ; i++) { 
            LocalTime dayformat=request.getHours().get(i);            
            DaySchedule day= dayScheduleMapper.EntityTo(daySchedule, dayformat, s);
            daySchedule.add(day) ;
            dayScheduleRepository.save(day);
        }            
         return daySchedule;
       
         }
    
    @Transactional
    private Professional userProfessional(){
        Professional professional;
        try {
            professional = (Professional) userService.getInfoUser();
        } catch (NotFoundException e) {
            throw new ParamNotFound("error: Username is not valid");
        } 
        return professional;
    }




   
    
}
