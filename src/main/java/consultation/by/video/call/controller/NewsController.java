package consultation.by.video.call.controller;

import consultation.by.video.call.model.request.NewRequest;
import consultation.by.video.call.model.response.NewResponse;
import consultation.by.video.call.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewService newService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewResponse createNew(@ModelAttribute NewRequest request,
                                 @RequestPart(value = "image", required = false) MultipartFile[] file){
        return newService.saveNew(request, file);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewResponse getNewBy(@PathVariable Long id){
        return newService.getNewBy(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<NewResponse> getAll(){
        List<NewResponse> response = newService.getAll();
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        newService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @ModelAttribute NewRequest request,@RequestPart(value = "image", required = false) MultipartFile[] file){
        newService.update(id, request, file);
    }






}
