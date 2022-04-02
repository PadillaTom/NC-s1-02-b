package consultation.by.video.call.controller;

import consultation.by.video.call.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class FirebaseController {

    private final FirebaseService firebaseService;

    @PostMapping("/uploadImage")
    public String saveImage(@RequestParam(name = "file") MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                String fileName = firebaseService.save(file);
                String imageUrl = firebaseService.getImageUrl(fileName);
                return imageUrl ;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return  "No se ha cargado.";
    }




}
