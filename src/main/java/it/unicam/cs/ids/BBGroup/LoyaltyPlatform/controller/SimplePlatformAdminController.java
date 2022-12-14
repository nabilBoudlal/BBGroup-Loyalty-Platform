package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.PlatformAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.PlatformAdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/PlatformAdmin")
public class SimplePlatformAdminController implements PlatformAdminController{

    @Autowired
    private PlatformAdminManager platformAdminManager;

    @Override
    @GetMapping("/{id}")
    public PlatformAdmin getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return platformAdminManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public PlatformAdmin create(@RequestBody PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        return platformAdminManager.create(object);
    }

    @Override
    public PlatformAdmin update(PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return platformAdminManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return platformAdminManager.exists(id);
    }
}
