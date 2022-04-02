
package consultation.by.video.call.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends User {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "patient_professional", joinColumns ={@JoinColumn(name = "idPatient")},
    inverseJoinColumns = {@JoinColumn(name = "idProfessional")})
    private List<Professional> professionals = new ArrayList<>();
}
