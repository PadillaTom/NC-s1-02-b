package consultation.by.video.call.auth.service.abstraction;

import consultation.by.video.call.auth.request.UserAuthenticatedRequest;
import consultation.by.video.call.auth.response.UserAuthenticatedResponse;

public interface IAuthenticationService {
    UserAuthenticatedResponse authentication(UserAuthenticatedRequest request);
}
