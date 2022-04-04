package consultation.by.video.call.auth.config.seeder;

import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.repository.ProfessionalRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import consultation.by.video.call.auth.entity.ListRole;
import consultation.by.video.call.model.entity.Role;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.auth.repository.IRoleRepository;
import consultation.by.video.call.auth.repository.IUserRepository;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseSeeders {

    private static final String PASSWORD = "12345678";
    private static final String HOST_EMAIL = "@test.com";
    private static final String DEFAULT_FIRST_NAME = "CallVideo";

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @EventListener
    public void seed(ContextRefreshedEvent event) throws IOException {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            createUsers();
        }

    }

    private void createRoles() {
        createRole(1L, ListRole.USER);
        createRole(2L, ListRole.ADMIN);
        createRole(3L, ListRole.PROFESSIONAL);
    }

    private void createUsers() {
        createUsers(ListRole.USER);
        createUsers(ListRole.ADMIN);
        createUsers(ListRole.PROFESSIONAL);
    }

    private void createUsers(ListRole applicationRole) {

        for (int index = 1; index < 4; index++) {
                User user = new User();
                user.setFirstName(DEFAULT_FIRST_NAME + index);
                user.setEmail(applicationRole.getName().toLowerCase() + index + HOST_EMAIL);
                user.setPassword(passwordEncoder.encode(PASSWORD));
                user.setCity("Garin " + index);
                user.setCountry("Argentina");
                user.setLastName("Apellido" + index);
                user.setAge(41);
                user.setDni("24876987");
                user.setProvince("Mendoza");
                List<Role> roles = new ArrayList<>();
                roles.add(roleRepository.findByName(applicationRole.getFullRoleName()));
                user.setRoles(roles);
                userRepository.save(user);

        }
    }

    private void createRole(Long id, ListRole applicationRole) {
        Role role = new Role();
        role.setId(id);
        role.setName(applicationRole.getFullRoleName());
        role.setDescription(applicationRole.getName());
        roleRepository.save(role);
    }

}
