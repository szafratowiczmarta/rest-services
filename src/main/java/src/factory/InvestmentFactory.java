package src.factory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvestmentFactory {
    public IInvestment createInvestment(Investment investment) {
        IInvestment inv = null;
        if (investment.getType().equals("sale")) {
            inv = new SaleInvestment();
            System.out.println("Sale Investment created");
        } else if (investment.getType().equals("rent")) {
            inv = new RentInvestment();
            System.out.println("Rent Investment created");
        }
        return inv;
    }
}
