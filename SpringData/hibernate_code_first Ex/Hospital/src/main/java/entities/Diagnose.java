package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity{

//    diagnose has a name and comments
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;

}
