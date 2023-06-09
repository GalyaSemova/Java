package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Game extends BaseEntity{
//    Games – Id, Home Team, Away Team, Home Goals, Away Goals, Date and Time of Game, Home team Win
//bet rate, Away Team Win Bet Rate, Draw Game Bet Rate, Round, Competition)


   @OneToOne
   @JoinColumn
   private Team homeTeam;

   @OneToOne
   @JoinColumn
   private Team awayTeam;

   @Column(name = "home_team_goals")
   private Short homeGoals;

   @Column(name = "away_team_goals")
   private Short awayGoals;

   @Column(name = "date_time")
   private Date date;

   @Column(name = "home_team_win_bet_rate")
   private Double homeTeamWinBetRate;

   @Column(name = "away_team_win_bet_rate")
   private Double awayTeamWinBetRate;

   @Column(name = "draw_game_bet_rate")
   private Double drawGameBetRate;

   @ManyToOne
   @JoinColumn
   private Round round;

   @ManyToOne
   @JoinColumn
   private Competition competition;








}
