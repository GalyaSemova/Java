package com.example.gamestore.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity{
//    A game has a title, trailer (YouTube Video Id), image thumbnail (URL), size, price, description and release
//date
    @Column(nullable = false)
    private String title;
    @Column
    private String trailer;
    @Column
    private String imageThumbnail;
    @Column
    private float size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private LocalDate releaseDate;


    public String printAllGames() {
        return this.title + " " + this.price;
    }

}
