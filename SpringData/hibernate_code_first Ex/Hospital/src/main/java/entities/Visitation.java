package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {
//    visitation has a date and comments
    @Column(nullable = false)
    private Date date;
    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    private Patient patient;
}
