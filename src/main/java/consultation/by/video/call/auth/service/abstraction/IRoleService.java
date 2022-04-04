package consultation.by.video.call.auth.service.abstraction;

import consultation.by.video.call.model.entity.Role;

public interface IRoleService {
    Role findBy(String name);

    Role getRol(Long idRol);
}
