package consultation.by.video.call.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE turn SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate day;
    private LocalTime hour;
    private boolean high;//alta
    @ManyToOne()
    private Professional professional;
    @ManyToOne()
    private Patient patient;
    private boolean deleted;
}
