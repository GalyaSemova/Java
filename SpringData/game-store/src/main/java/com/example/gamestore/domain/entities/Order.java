package com.example.gamestore.domain.entities;

import jakarta.persistence.*;
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
@Table(name = "orders")
public class Order extends BaseEntity{
//    h order has a single buyer (user) and one or many products.
    @ManyToOne
    private User buyer;
    @ManyToMany(targetEntity = Game.class, fetch = FetchType.EAGER)
    private Set<Game> games;

    public Order() {

    }
}
