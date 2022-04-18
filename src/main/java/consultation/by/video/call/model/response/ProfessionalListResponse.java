package consultation.by.video.call.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import consultation.by.video.call.model.entity.Profession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @AllArgsConstructor
@Getter @Setter

public class ProfessionalListResponse {

    private Long id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private int age;
    private String dni;
    private String image_url;
    private String country;
    private String province;
    private String city;
    private String enrollment;
    private double consultationPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Profession profession;

}
