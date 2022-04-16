package consultation.by.video.call.controller;

import consultation.by.video.call.model.request.DayScheduleRequest;
import consultation.by.video.call.model.request.ScheduleRequest;
import consultation.by.video.call.model.response.DayScheduleResponse;
import consultation.by.video.call.service.DayScheduleService;
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
@RequestMapping("/api/v1/dayschedule")
@Api(value = "controller for dayschedule methods", description = "This API for dayschedules methods")
public class DayScheduleController {
    
    @Autowired
    private DayScheduleService dayScheduleService;
    
    //controlador de prueba
    @PostMapping("/add")
    @ApiOperation(value = "Add info Day's Schedule the Professional ", notes = "Return day's schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public DayScheduleResponse addDaySchedule(@Valid @RequestBody DayScheduleRequest request) {
        return dayScheduleService.save(request);
    }
   
   
}
//@RequestParam