package consultation.by.video.call.controller;

import consultation.by.video.call.model.request.ScheduleRequest;
import consultation.by.video.call.model.response.ScheduleResponse;
import consultation.by.video.call.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
@Api(value = "controller for schedule methods", description = "Schedule API for turns methods")
public class ScheduleController {
    
    @Autowired
     private ScheduleService scheduleService;
    @PostMapping("/add")
    @ApiOperation(value = "Add info Turn patient  & professional", notes = "Return patient")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponse addSchedule(@Valid @RequestBody ScheduleRequest request) {
        return scheduleService.save(request);
    }
    
  
}
