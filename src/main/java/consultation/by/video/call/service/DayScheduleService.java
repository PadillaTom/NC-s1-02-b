package consultation.by.video.call.service;

import consultation.by.video.call.model.request.DayScheduleRequest;
import consultation.by.video.call.model.response.DayScheduleResponse;


public interface  DayScheduleService {
 
    DayScheduleResponse save(DayScheduleRequest request);
}
