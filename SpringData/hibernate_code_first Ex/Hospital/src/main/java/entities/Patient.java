package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{

//    patients. Each patient has a first name, last name, address, email, date of birth, picture, information on whether
//he has medical insurance or not.

//   history of all his visitations, diagnoses and prescribed
//medicaments.

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column
   private String address;

   @Column(unique = true)
   private String email;

   @Column(name = "date_of_birth")
   private Date dateOfBirth;

   @Column
   private String picture;

   @Column(name= "has_medical_insurance", nullable = false)
   private boolean hasMedicalInsurance;


   @OneToMany(mappedBy = "patient",cascade = CascadeType.PERSIST)
   private Set<Visitation> visitations;


   @ManyToMany (cascade = CascadeType.PERSIST)
   @JoinTable(name = "patients_diagnoses",
           joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
   private Set<Diagnose> diagnoses;


   @ManyToMany(cascade = CascadeType.PERSIST)
   @JoinTable(name = "patients_medicaments",
           joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
   private Set<Medicament> medicaments;




}
