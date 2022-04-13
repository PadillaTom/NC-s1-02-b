package consultation.by.video.call.service.impl;

import consultation.by.video.call.auth.repository.IUserRepository;
import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;


public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private IUserRepository userRepository; 
    
    @Override
    public Patient getSchedules(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PatientTurnResponse saveSchedule(PatientTurnRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
