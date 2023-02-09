package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Employee;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Employee")
public class SimpleEmployeeController implements EmployeeController{
    @Autowired
    private EmployeeManager employeeManager;

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
}
