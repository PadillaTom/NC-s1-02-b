package consultation.by.video.call.service;

import consultation.by.video.call.model.request.ScheduleRequest;
import consultation.by.video.call.model.response.ScheduleResponse;

public interface  ScheduleService {
   ScheduleResponse save(ScheduleRequest request);

}
