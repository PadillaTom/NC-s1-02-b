package consultation.by.video.call.controller;


import consultation.by.video.call.auth.response.PatientsReponse;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/patient/api/v1")
@Api(value = "controller for patient methods", description = "This API for patient methods")
public class PatientController {

  
    @Autowired
    private PatientService patientService;

    @PostMapping("/turn_patient")
    @ApiOperation(value = "Add info Turn patient  & professional", notes = "Return patient")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientTurnResponse createTurnPatient(@Valid @RequestBody PatientTurnRequest request) {
        return patientService.savePatientTurn(request);
    }
    
    @GetMapping("/patients")
    @ApiOperation(value = "Show all patients", notes = "Return List patients")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PatientsReponse> getAllPatients() {
        return patientService.getPatients();
    }
    

}
