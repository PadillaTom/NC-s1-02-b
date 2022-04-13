package consultation.by.video.call.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewResponse {

    private Long id;
    private String title;
    private String imageUrl;
    private String source; // fuente
    private boolean deleted = Boolean.FALSE;
}
