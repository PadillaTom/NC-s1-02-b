
package consultation.by.video.call.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends User {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "patient_professional", joinColumns ={@JoinColumn(name = "idPatient")},
    inverseJoinColumns = {@JoinColumn(name = "idProfessional")})
    private List<Professional> professionals = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    List<Turn> turnList = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    List<Chart> chartsList;
        
    @NotBlank
    @Size(min = 10, max = 30, message = "Occupation must be between 10 and 30 characters long")
    private String Occupation;
    @NotBlank
    @Size(min = 10, max = 30, message = "Marital Status must be between 10 and 30 characters long")
    private String maritalStatus;
    @NotBlank
    @Size(min = 10, max = 30, message = "Religion must be between 10 and 30 characters long")
    private String religion;

    public void addTurn(Turn t){
        turnList.add(t);
    }
}
