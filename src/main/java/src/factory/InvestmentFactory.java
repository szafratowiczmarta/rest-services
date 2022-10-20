package src.factory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvestmentFactory {
    public static IInvestment createInvestment(Investment investment) {
        IInvestment inv = null;
        String investmentType = investment.getType();
        switch (investmentType) {
            case "sale":
                inv = new SaleInvestment();
                break;
            case "rent": 
                inv = new RentInvestment();
                break;
        }
        return inv;
    }
}
