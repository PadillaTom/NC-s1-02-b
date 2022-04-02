package consultation.by.video.call.auth.service;

import consultation.by.video.call.model.entity.Role;
import consultation.by.video.call.auth.repository.IRoleRepository;
import consultation.by.video.call.auth.service.abstraction.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findBy(String name) {
        return roleRepository.findByName(name);
    }
}
