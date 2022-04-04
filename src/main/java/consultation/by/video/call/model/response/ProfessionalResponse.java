package consultation.by.video.call.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ProfessionalResponse {

    private Long id;
    private String email;
    private String token;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
