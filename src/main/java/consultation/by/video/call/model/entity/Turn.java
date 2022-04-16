package consultation.by.video.call.model.entity;

import consultation.by.video.call.model.enums.EnumState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;

@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE turn SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Builder
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate day; //date full
    private LocalTime hour;
    @Enumerated(value = EnumType.STRING)
    private EnumState high;//alta
    @ManyToOne(fetch = FetchType.LAZY)
    private Professional professional;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    private boolean deleted;
}
