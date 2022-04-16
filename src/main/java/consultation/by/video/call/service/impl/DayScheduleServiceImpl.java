package consultation.by.video.call.service.impl;

import consultation.by.video.call.repository.IUserRepository;
import consultation.by.video.call.model.mapper.TurnMapper;
import consultation.by.video.call.model.request.DayScheduleRequest;
import consultation.by.video.call.model.response.DayScheduleResponse;
import consultation.by.video.call.repository.TurnRepository;
import consultation.by.video.call.service.DayScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayScheduleServiceImpl implements DayScheduleService {

    @Autowired
    private IUserRepository userRepository; 
    @Autowired
    private TurnMapper turnMapper;
    @Autowired
    private TurnRepository turnRepository;

    @Override
    public DayScheduleResponse save(DayScheduleRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 

   
}
