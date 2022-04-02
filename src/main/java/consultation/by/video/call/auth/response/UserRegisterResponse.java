package consultation.by.video.call.auth.response;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    private Long id;
    private String email;  
    private String token;
    private String firstName;
    private String lastName;
    private RoleResponse roles;
   


}
