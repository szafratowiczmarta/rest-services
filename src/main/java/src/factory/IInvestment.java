package src.factory;

import java.time.LocalDate;

public interface IInvestment {

    double cost(Investment investment);
    LocalDate calculateEndDate(Investment investment);

}