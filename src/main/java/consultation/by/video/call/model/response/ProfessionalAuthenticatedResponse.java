package consultation.by.video.call.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
public class ProfessionalAuthenticatedResponse {
    private String token;
    private String email;
    private Collection<? extends GrantedAuthority> roleName;



    public ProfessionalAuthenticatedResponse(String token, String email, Collection<? extends GrantedAuthority> roleName) {
        this.token= token;
        this.email = email;
        this.roleName = roleName;
    }

}
