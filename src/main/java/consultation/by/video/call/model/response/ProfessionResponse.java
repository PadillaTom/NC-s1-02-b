package consultation.by.video.call.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
}
