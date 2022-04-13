package consultation.by.video.call.model.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRequest {

    private String title;
    private String imageUrl;
    private String source; // fuente
    private boolean deleted = Boolean.FALSE;

}
