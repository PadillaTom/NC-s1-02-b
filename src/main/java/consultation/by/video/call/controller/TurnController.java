package consultation.by.video.call.controller;

import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import consultation.by.video.call.service.TurnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/turn")
@Api(value = "controller for turn methods", description = "This API for turns methods")
public class TurnController {
    
    @Autowired
    private TurnService turnService;

    
    @GetMapping("")
    @ApiOperation(value = "Show all turns", notes = "Return List Turns")  
    public List<TurnsPatientResponse> getAllTurn() {
        return turnService.getAllTurns();
    }
  
    @ApiOperation(value = "show shifts according to your states", notes = "Return List according to your state")  
    @GetMapping("/turns/high")
     public List<TurnsPatientResponse> getTurnCalceled(EnumState state) {
        return turnService.getAllTurnsActived(state);
    }
    
   @ApiOperation(value = "show turn by ID", notes = "Return Turn by ID")  
    @GetMapping("/{id}")
     public TurnsPatientResponse getTurnById(@PathVariable Long id) {
        return turnService.getTurnById(id);
    }
   
}
//@RequestParam