package consultation.by.video.call.auth.service.abstraction;

import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.auth.request.UserRequest;
import consultation.by.video.call.auth.response.PatientsReponse;
import consultation.by.video.call.auth.response.UserResponse;
import consultation.by.video.call.auth.response.UserRoleResponse;
import consultation.by.video.call.model.entity.Role;
import java.util.List;
import javassist.NotFoundException;
import javax.persistence.EntityNotFoundException;

public interface IUserService {
    
    User getInfoUser() throws NotFoundException;
    void delete(Long id)throws EntityNotFoundException;
    UserResponse update(Long id, UserRequest request)throws NotFoundException;
    UserResponse getById(Long id);
    List<UserResponse> getAllUser();
    UserRoleResponse updateRoles(Long id , List<Role> roles);
    List<UserResponse> getByFilters(String email, String first_name, String last_name, String dni);
}
