package com.example.bookshop.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author extends BaseEntity{

//Author - id, first name (optional) and last name

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

//    @OneToMany(targetEntity = Book.class, mappedBy = "author", fetch = FetchType.EAGER)
//    public List<Book> books;


    public String getAuthorFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getAuthorFullNameAndCountOfBooks() {
//        return this.firstName + " " + this.lastName; + " " + books.size();
        return this.firstName + " " + this.lastName;
    }
}
