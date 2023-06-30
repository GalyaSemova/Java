package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
//    Student (id, first name, last name, phone number, average grade, attendance)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "average_grade", scale = 2, precision = 5)
    private Double averageGrade;

    @Column
    private boolean attendance;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

}
