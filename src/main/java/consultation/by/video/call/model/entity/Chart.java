
package consultation.by.video.call.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.enums.EnumState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE person SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ApiModel("Model Chart")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    @Column(name = "date_session", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotBlank
    @Size(min = 10, max = 255, message = "Reason must be between 10 and 255 characters long") 
    private String reasonForConsultation;       //Motivo de la consulta
    @NotBlank
    @Size(min = 10, max = 255, message = "Appearance must be between 10 and 255 characters long") 
    private String appearanceAndAttitude;       //Apariencia general y actitud
    @NotBlank
    @Size(min = 10, max = 255, message = "Conscious must be between 10 and 255 characters long") 
    private String consciousnessState;          //Estado de conciencia
    @NotBlank
    @Size(min = 10, max = 255, message = "Mood must be between 10 and 255 characters long") 
    private String currentMood;                 //Estado de ánimo
    @NotBlank
    @Size(min = 10, max = 255, message = "Test Result must be between 10 and 255 characters long") 
    private String testResult;                  //Resultado del examen
    private String observations;
    @ManyToOne
    @JoinColumn(name = "fk_chart", nullable = false, updatable = false)
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "fk_professional", updatable = false, nullable = false)
    private Professional professional;
    
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(value = "the chart state", required = true)
    private EnumState enumState;
}
