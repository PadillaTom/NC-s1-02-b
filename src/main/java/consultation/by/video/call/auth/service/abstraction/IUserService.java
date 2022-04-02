package consultation.by.video.call.auth.service.abstraction;


import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.auth.request.UserRegisterRequest;
import consultation.by.video.call.auth.response.UserResponse;
import java.util.List;
import javassist.NotFoundException;
import javax.persistence.EntityNotFoundException;

public interface IUserService {
    User getInfoUser() throws NotFoundException;

    void delete(Long id)throws EntityNotFoundException;

//    UserUpdateResponse update(Long id, UserRegisterRequest request)throws NotFoundException;

    UserResponse getById(Long id);
    List<UserResponse> getAllUser();
}
