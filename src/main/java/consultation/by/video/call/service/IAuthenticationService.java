package consultation.by.video.call.service;

import consultation.by.video.call.model.request.UserAuthenticatedRequest;
import consultation.by.video.call.model.response.UserAuthenticatedResponse;

public interface IAuthenticationService {
    UserAuthenticatedResponse authentication(UserAuthenticatedRequest request);
}
