package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.New;
import consultation.by.video.call.model.request.NewRequest;
import consultation.by.video.call.model.response.NewResponse;
import consultation.by.video.call.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class NewMapper {

    @Autowired
    private FirebaseService service;

    public  New toEntityMapper(NewRequest request, MultipartFile[] file){
        String imageUrl = service.subirImagen(file);
        New entity = new New();
        entity.setTitle(request.getTitle());
        entity.setSource(request.getSource());
        entity.setImageUrl(imageUrl);
        return entity;
    }

    public NewResponse toDtoMapper(New entity){
        NewResponse response = new NewResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setSource(entity.getSource());
        response.setImageUrl(entity.getImageUrl());
        return response;
    }

}
