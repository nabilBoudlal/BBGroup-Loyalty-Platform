package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Employee;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleEmployeeManager implements EmployeeManager{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee getInstance(Long id) throws EntityNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() ->
                 new EntityNotFoundException("Attività  con id: "+id+" non trovata!"));
    }

    @Override
    public Employee create(Employee object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesNotNull(object);
        checkCreation(object);
        return employeeRepository.save(object);
    }

    @Override
    public Employee update(Employee object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    private void checkCreation(Employee employee) throws EntityNotFoundException, IdConflictException {
        if(employeeRepository.existsByEmail(employee.getEmail())) throw new IdConflictException("Dipendente già presente");
    }

    private void checkIfValuesNotNull(Employee employee) throws NullPointerException {
        Objects.requireNonNull(employee.getEmail(), "Email non inserita");
        Objects.requireNonNull(employee.getPhone(), "Telefono non inserito");

    }

}
