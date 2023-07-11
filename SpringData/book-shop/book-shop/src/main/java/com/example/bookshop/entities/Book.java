package com.example.bookshop.entities;

import com.example.bookshop.enums.AgeRestriction;
import com.example.bookshop.enums.EditionType;


import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book extends BaseEntity{

// Book - id, title (between 1...50 symbols), description (optional, up to 1000 symbols), edition type (NORMAL,
//PROMO or GOLD), price, copies, release date (optional), age restriction (MINOR, TEEN or ADULT)
    @Column(nullable = false, length = 50)
    private String title;

    //    @Column(name = "edition_type")
    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

//    @Column(name = "age_restriction")
    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    Set<Category> categories;

    @Column(nullable = false)
    private Integer copies;

    @Column(length = 1000)
    private String description;



}

