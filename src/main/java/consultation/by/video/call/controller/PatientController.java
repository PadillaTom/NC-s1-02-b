package consultation.by.video.call.controller;


import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientResponseById;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.model.response.PatientsReponse;
import consultation.by.video.call.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    //TODO devuelve un paciente por id con sus turnos
    @GetMapping("/{id}")
    @ApiOperation(value = "get patient by id", notes = "returns a patient by id with their shifts")
    public ResponseEntity<PatientResponseById> getPatientById(@PathVariable Long id){
        PatientResponseById response = patientService.getPatientById(id);
        return ResponseEntity.ok().body(response);
    }


}
