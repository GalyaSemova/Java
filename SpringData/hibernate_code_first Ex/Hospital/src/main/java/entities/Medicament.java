package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity{
//    medicament has a name.
    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<Patient> patients;


}
