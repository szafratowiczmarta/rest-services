package src.factory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static src.GlobalConstants.MAX_COST_FOR_SINGLE_SALE_APARTMENT;

public class SaleInvestment extends Investment implements IInvestment {

    @Override
    public double cost(Investment investment) {
        Double cost = investment.getNumberOfApartments() * MAX_COST_FOR_SINGLE_SALE_APARTMENT;
        System.out.println(String.format("Cost for rent investment with %s apartments is %s", investment.getNumberOfApartments(), cost));
        return cost;
    }

    @Override
    public LocalDate calculateEndDate(Investment investment) {
        int numberOfApartments = investment.getNumberOfApartments();
        int numberOfDays = numberOfApartments * 20;
        Date startDate = investment.getStartDate();
        LocalDate endDate = LocalDateTime.from(startDate.toInstant().atZone(ZoneId.of("CET"))).plusDays(numberOfDays).toLocalDate();
        System.out.println(String.format("End Date of investment with %s apartments is %s", investment.getNumberOfApartments(), endDate.toString()));
        return endDate;
    }


}
