package consultation.by.video.call.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter @Setter
public class Professional extends User {

    private String registrationNumber;
    private double consultationPrice;


    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "professionals")
    private List<Patient> patients = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "professional_profession", joinColumns ={@JoinColumn(name = "idProfessional")},
            inverseJoinColumns = {@JoinColumn(name = "idProfession")})
    private List<Profession> professions = new ArrayList<>();




}
