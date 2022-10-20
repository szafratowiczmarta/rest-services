package src.factory;

import java.time.LocalDate;

public interface IInvestment {

    double calculateCost(Investment investment);
    LocalDate calculateEndDate(Investment investment);

}