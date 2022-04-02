package consultation.by.video.call.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionRequest {

    @NotEmpty(message = "Es Requerido.")
    private String title;

    private String description;

    private String imageUrl;
}
