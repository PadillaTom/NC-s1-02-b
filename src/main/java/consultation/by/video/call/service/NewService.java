package consultation.by.video.call.service;

import consultation.by.video.call.model.request.NewRequest;
import consultation.by.video.call.model.response.NewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewService {
    NewResponse saveNew(NewRequest request, MultipartFile[] file);

    NewResponse getNewBy(Long id);

    List<NewResponse> getAll();

    void deleted(Long id);


    void update(Long id, NewRequest request, MultipartFile[] file);
}
