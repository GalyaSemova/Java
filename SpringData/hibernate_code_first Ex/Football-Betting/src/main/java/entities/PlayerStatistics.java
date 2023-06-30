package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {

// Game, Player, Scored Goals, Player Assists, Played Minutes During Game, (PK =
//Game + Player)
    @Id
    @ManyToOne
    @JoinColumn
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn
    private Player player;

    @Column(name = "scored_goals")
    private Integer scoredGoals;

    @Column(name = "player_assists")
    private Short playerAssists;

    @Column(name = "played_minutes")
    private Short playedMinutes;

}
