package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
public class BetGame implements Serializable {
//    BetGame – Game, Bet, Result Prediction (PK = Game + Bet)
 @Id
 @OneToOne
 private Game game;

 @Id
 @OneToOne
 private Bet bet;

 @OneToOne
 @JoinColumn
 private ResultPrediction resultPrediction;

}

