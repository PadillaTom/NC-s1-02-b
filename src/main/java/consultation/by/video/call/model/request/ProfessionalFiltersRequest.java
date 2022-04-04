package consultation.by.video.call.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProfessionalFiltersRequest {

    private String email;
    private String first_name;
    private String last_name;
    private String dni;
}
