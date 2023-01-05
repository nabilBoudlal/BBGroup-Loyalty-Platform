package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.CostumerJoinRequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/CostumerRequest")
public class SimpleCostumerJoinRequestController implements CostumerJoinRequestController{

    @Autowired
    private CostumerJoinRequestManager requestManager;

    @Override
    @GetMapping("/{id}")
    public CostumerJoinRequest getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return requestManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public CostumerJoinRequest create(@RequestBody CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return requestManager.create(object);
    }

    @Override
    public CostumerJoinRequest update(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
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
