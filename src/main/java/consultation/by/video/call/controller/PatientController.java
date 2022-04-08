package consultation.by.video.call.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "*")
@RequestMapping("/patient")
@Api(value = "controller for patient methods", description = "This API for patient methods")
public class PatientController {

}
