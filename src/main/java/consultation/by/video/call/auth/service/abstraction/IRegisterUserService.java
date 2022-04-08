package consultation.by.video.call.auth.service.abstraction;

import consultation.by.video.call.auth.request.UserRegisterRequest;
import consultation.by.video.call.auth.response.UserRegisterResponse;
import org.springframework.web.multipart.MultipartFile;



public interface IRegisterUserService {

    UserRegisterResponse register(UserRegisterRequest request, MultipartFile[] file);
}
