
package consultation.by.video.call.auth.response;

import consultation.by.video.call.model.entity.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {
    private Long id;   
    private String firt_name;    
    private boolean modified_role=true;
}
