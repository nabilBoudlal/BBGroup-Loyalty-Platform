package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.EmployeeJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.EmployeeJoinRequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/EmployeeRequest")
public class SimpleEmployeeJoinRequestController implements EmployeeJoinRequestController{

    @Autowired
    private EmployeeJoinRequestManager requestManager;
    @Override
    @GetMapping("/{id}")
    public EmployeeJoinRequest getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return requestManager.getInstance(id);
    }
    @Override
    @PostMapping("/createNew")
    public EmployeeJoinRequest create(@RequestBody EmployeeJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return requestManager.create(object);
    }

    @Override
    public EmployeeJoinRequest update(EmployeeJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return requestManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(Long id) {
        return requestManager.exists(id);
    }
}

