package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Employee;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.TransactionRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Employee")
public class SimpleEmployeeController implements EmployeeController{
    @Autowired
    private EmployeeManager employeeManager;

    @Autowired
    private TransactionController transactionController;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/{id}")
    @Override
    public Employee getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return employeeManager.getInstance(id);
    }
    @PostMapping("/createNew")
    @Override
    public Employee create(@RequestBody Employee object) throws EntityNotFoundException, IdConflictException {
        return employeeManager.create(object);
    }

    @Override
    public Employee update(Employee object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return employeeManager.delete(id);
    }

    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(Long id) {
        return employeeManager.exists(id);
    }

    @PostMapping("/validateTransaction/{costumerEmail}")
    public Iterable<Transaction> validateCostumerTransaction(@PathVariable String costumerEmail) throws IdConflictException, EntityNotFoundException {
       Costumer cst= costumerRepository.findByEmail(costumerEmail);
       Optional<Transaction> trs= transactionRepository
               .findById(
                       cst.getFidelityCard()
                               .getTransactions()
                               .stream()
                               .filter(s-> !s.isValidated())
                               .findFirst()
                               .orElseThrow()
                               .getTransactionId()
               );
       transactionController.validateTransaction(cst.getFidelityCard().getCardId(),trs.orElseThrow().getTransactionId());
       return cst.getFidelityCard().getTransactions();
    }
}
