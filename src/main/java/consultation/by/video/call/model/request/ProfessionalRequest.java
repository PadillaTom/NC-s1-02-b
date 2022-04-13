package consultation.by.video.call.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProfessionalRequest {
    @NotNull(message = "El email no puede estar vacío")
    private String email;
    @NotNull (message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 25, message = "La contraseña debe ser entre 6 y 25 caracteres")
    private String password;
    @NotNull(message = "El nombre no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El nombre no puede contener números")
    private String first_name;
    @NotNull(message = "El Apellido no puede estar vacío ni ser nulo")
    @Pattern(regexp = "[a-zA-Z]+", message = "El apellido no puede contener números")
    private String last_name;
    @NotNull (message = "La edad no puede estar vacío")
    private int age;
    @NotNull (message = "El dni no puede estar vacío")
    private String dni;
    private String image_url;
    @NotNull(message = "El Pais no puede estar vacío")
    private String country;
    @NotNull(message = "Lap provincia no puede estar vacía ")
    private String province;
    @NotNull(message = "La ciudad no puede estar vacía")
    private String city;
    @NotNull(message = "El numero de matricula no puede estar vacío ")
    private String enrollment;
    @NotNull(message = "Ingrese un precio de consulta")
    private double consultationPrice;

    private Long professionId;
    private MultipartFile file;
}
