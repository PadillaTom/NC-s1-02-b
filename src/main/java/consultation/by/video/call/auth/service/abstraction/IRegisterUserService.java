package consultation.by.video.call.auth.service.abstraction;

import consultation.by.video.call.auth.request.UserRegisterRequest;
import consultation.by.video.call.auth.response.UserRegisterResponse;



public interface IRegisterUserService {

    UserRegisterResponse register(UserRegisterRequest request);
}
