package entities;

import entities.enums.ResultPredictionValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity{

//    Prediction (possible values - Home Team Win, Draw Game, Away Team Win)
    @Enumerated(EnumType.STRING)
    private ResultPredictionValue prediction;
}
