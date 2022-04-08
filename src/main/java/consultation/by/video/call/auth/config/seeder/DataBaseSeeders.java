package consultation.by.video.call.auth.config.seeder;

import consultation.by.video.call.model.entity.Professional;
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
import consultation.by.video.call.model.entity.Patient;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseSeeders {

    private static final String PASSWORD = "12345678";
    private static final String HOST_EMAIL = "@test.com";
    private static final String firstNameUser[] = {"Gabriel", "Abel", "Tomas"};
    private static final String firstNamePatient[] = {"Laura", "Raquel", "Roberto"};
    private static final String firstNameProfessional[] = {"Tomas", "Eduardo", "Caro"};
    private static final String lastNameUser[] = {"Navarro", "Acevedo", "Padilla"};
    private static final String lastNameProfessional[] = {"Caruana", "Lopez", "Nose"};
    private static final String lastNamePatient[] = {"Sanchez", "Lepez", "Astudillo"};

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
        createRole(4L, ListRole.PATIENT);
    }

    private void createUsers() {
        User user = new User();
        createUsers(ListRole.ADMIN);
        createPatient(ListRole.USER);
        createProfessional(ListRole.PROFESSIONAL);
    }

    private Integer calcAge() {
        return (int) (Math.random() * (90 - 18 + 1) + 18);
    }

    private String calcDni() {
        Integer num = (int) (Math.random() * (99000000 - 10000000 + 1) + 10000000);
        return num.toString();
    }

    private void createUsers(ListRole applicationRole) {

        for (int index = 0; index < 3; index++) {
            User user = new User();
            user.setFirstName(firstNameUser[index]);
            user.setEmail(applicationRole.getName().toLowerCase() + (index + 1) + HOST_EMAIL);
            user.setPassword(passwordEncoder.encode(PASSWORD));
            user.setCity("Beltran ");
            user.setCountry("Argentina");
            user.setLastName(lastNameUser[index]);
            user.setAge(calcAge());
            user.setDni(calcDni());
            user.setProvince("Mendoza");
            user.setRoles(createListRole(applicationRole));
            userRepository.save(user);

        }
    }

    private List<Role> createListRole(ListRole applicationRole) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(applicationRole.getFullRoleName()));
        return roles;
    }

    private void createPatient(ListRole applicationRole) {

        for (int index = 0; index < 3; index++) {
            Patient user = new Patient();
            user.setFirstName(firstNamePatient[index]);
            user.setEmail(applicationRole.getName().toLowerCase() + (index + 1) + HOST_EMAIL);
            user.setPassword(passwordEncoder.encode(PASSWORD));
            user.setCity("Beltran ");
            user.setCountry("Argentina");
            user.setLastName(lastNamePatient[index]);
            user.setAge(calcAge());
            user.setDni(calcDni());
            user.setProvince("Mendoza");
            user.setRoles(createListRole(applicationRole));
            userRepository.save(user);

        }
    }

    private void createProfessional(ListRole applicationRole) {

        for (int index = 0; index < 3; index++) {
            Professional user = new Professional();
            user.setFirstName(firstNameProfessional[index]);
            user.setEmail(applicationRole.getName().toLowerCase() + (index + 1) + HOST_EMAIL);
            user.setPassword(passwordEncoder.encode(PASSWORD));
            user.setCity("Beltran ");
            user.setCountry("Argentina");
            user.setLastName(lastNameProfessional[index]);
            user.setAge(calcAge());
            user.setDni(calcDni());
            user.setProvince("Mendoza");
            user.setRoles(createListRole(applicationRole));
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
