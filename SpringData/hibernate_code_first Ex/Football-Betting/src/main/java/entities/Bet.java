package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Bet extends BaseEntity{
    //Bets – Id, Bet Money, Date and Time of Bet, User
    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "bet_date")
    private Date betDate;

    @ManyToOne
    private User user;

}
