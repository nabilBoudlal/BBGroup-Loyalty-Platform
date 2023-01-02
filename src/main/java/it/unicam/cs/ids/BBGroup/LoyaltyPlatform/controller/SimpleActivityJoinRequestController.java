package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityJoinRequestManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.PlatformAdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ActivityRequest")
public class SimpleActivityJoinRequestController implements ActivityJoinRequestController{

    @Autowired
    private ActivityJoinRequestManager requestManager;

    @Override
    @GetMapping("/{id}")
    public ActivityJoinRequest getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return requestManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public ActivityJoinRequest create(@RequestBody ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return requestManager.create(object);
    }

    @Override
    public ActivityJoinRequest update(ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
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
