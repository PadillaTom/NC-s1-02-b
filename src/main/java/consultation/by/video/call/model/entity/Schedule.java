package consultation.by.video.call.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    @NotNull(message = "You must enter a valid date") 
    private String weekday; //lunes, Martes ....
    @JsonFormat(pattern="dd/MM/yyyy")    
    private LocalDate dayMonthYear;//dia del mes
    @JsonFormat(pattern="HH:mm")
    @NotNull(message = "You must enter a valid hour")
    private LocalTime homework; // Inicio del horario laboral de cada profesional
    @JsonFormat(pattern="HH:mm")
    @NotNull(message = "You must enter a valid hour")  
    private LocalTime endOfWork; //Fin del horario laboral de cada profesional
    @OneToOne
    @JoinColumn(name = "fk_professional", updatable = false, nullable = false)
    private Professional professional;
    @Enumerated(value = EnumType.STRING)
    private EnumState status=EnumState.ACTIVED;

    public Schedule(LocalTime inicioLaboral, LocalTime finLaboral) {
        this.homework = LocalTime.of(8,0);
        this.endOfWork = LocalTime.of(12,0);
    }


}
