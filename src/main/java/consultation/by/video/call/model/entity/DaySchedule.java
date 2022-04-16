package consultation.by.video.call.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import consultation.by.video.call.model.enums.EnumState;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id; 
    @JsonFormat(pattern="HH:mm")
    @NotNull(message = "You must enter a valid hour") 
    private LocalTime hoursWork;
    @Enumerated(value = EnumType.STRING)
    private EnumState status;
    
    @ManyToOne
//    @JoinColumn(name = "fk_schedule", updatable = false, nullable = false)
    private Schedule schedule;
    
    public DaySchedule(LocalTime hoursWork, EnumState status, Schedule schedule) {
        this.hoursWork = hoursWork;
        this.status = status;
        this.schedule=schedule;
    }
}
