package consultation.by.video.call.model.response;

import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Turn;
import lombok.*;

import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class PatientResponseById {
    private Long id;
    private String username;
    private String firt_name;
    private String last_name;
    private String dni;
    private String image_url;
    private List<TurnsPatientResponse> turns;


}
