package consultation.by.video.call.controller;

import consultation.by.video.call.model.request.ProfessionRequest;
import consultation.by.video.call.model.response.ProfessionResponse;
import consultation.by.video.call.service.ProfessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profession")
@RequiredArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionResponse create(@Valid @RequestBody ProfessionRequest request){
        return professionService.saveProfession(request);
    }

    @GetMapping
    public List<ProfessionResponse> getAllProfessions(){
        return professionService.getProfessions();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody ProfessionRequest request){
        professionService.updateProfession(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        professionService.deleteProfession(id);
    }

}
