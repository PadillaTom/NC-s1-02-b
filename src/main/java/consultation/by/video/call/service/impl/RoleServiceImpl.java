package consultation.by.video.call.service.impl;

import consultation.by.video.call.service.IRoleService;
import consultation.by.video.call.model.entity.Role;
import consultation.by.video.call.repository.IRoleRepository;

import java.util.List;
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

    @Override
    public Role findById(Long id) {
       return roleRepository.getById(id);    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
