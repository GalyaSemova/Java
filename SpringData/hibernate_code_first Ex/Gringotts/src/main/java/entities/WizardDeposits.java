package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(name = "first_name", length = 50)
      private String firstName;
      @Column(name = "last_name", length = 60)
      private String lastName;
      @Column(length = 1000, columnDefinition = "TEXT")
      private String note;
      @Column(nullable = false)
      private Integer age;
      @Column(name = "magic_wand_creator", length = 100)
      private String magicWandCreator;
      @Column(name = "magic_wand_size")
      private Integer magicWandSize;
      @Column(name = "deposit_group", length = 20)
      private String depositGroup;
      @Column(name = "deposit_start_date")
      private Date depositStartDate;
      @Column(name = "deposit_amount", scale = 2, precision = 10)
      private BigDecimal depositAmount;
      @Column(name = "deposit_interest", scale = 2, precision = 2)
      private BigDecimal depositInterest;
      @Column(name = "deposit_charge", scale = 2, precision = 10)
      private BigDecimal depositCharge;
      @Column(name = "deposit_expiration_date")
      private Date depositExpirationDate;
      @Column(name = "is_deposit_expired")
      private boolean isDepositExpired;

}
