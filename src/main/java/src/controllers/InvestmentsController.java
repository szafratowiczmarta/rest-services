package src.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.factory.IInvestment;
import src.factory.Investment;
import src.factory.InvestmentFactory;
import src.model.InvestmentRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class InvestmentsController {

    @Autowired
    InvestmentRepository investmentRepository;

    @GetMapping("/investments")
    List<Investment> getInvestments() {
        return investmentRepository.findAll();
    }

    @PostMapping("/investment")
    Investment postInvestment(@RequestBody Investment investment) {
        InvestmentFactory investmentFactory = new InvestmentFactory();
        IInvestment inv = investmentFactory.createInvestment(investment);
        investment.setEndDate(inv.calculateEndDate(investment));
        investment.setCost(inv.cost(investment));
        return investmentRepository.save(investment);
    }

    @DeleteMapping("/investment/{id}")
    void deleteInvestment(@PathVariable Integer id) { investmentRepository.deleteById(id); }

}
