package consultation.by.video.call.service;

import consultation.by.video.call.model.request.UserRegisterRequest;
import consultation.by.video.call.model.response.UserRegisterResponse;
import org.springframework.web.multipart.MultipartFile;



public interface IRegisterUserService {

    UserRegisterResponse register(UserRegisterRequest request, MultipartFile[] file);
}
