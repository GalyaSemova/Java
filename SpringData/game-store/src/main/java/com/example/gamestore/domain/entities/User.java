package com.example.gamestore.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
//    Users can register in the system. After successful registration,
//    the user has email, password, full name, list
//of games and information on whether he/she is an administrator or not

//    ^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-]+)(\.[a-zA-Z]{2,5}){1,2}$
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 100)
    private String fullName;
    @Column(nullable = false)
    private Boolean isAdmin;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;
    @OneToMany(targetEntity = Order.class, mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Order> orders;


    public User() {

    }
}
