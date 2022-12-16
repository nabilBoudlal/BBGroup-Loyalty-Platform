package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Activity")
public class SimpleActivityController implements ActivityController{
    @Autowired
    private ActivityManager activityManager;
    @Override
    @GetMapping("/{id}")
    public Activity getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return activityManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Activity create(@RequestBody Activity object) throws IdConflictException, EntityNotFoundException {
        return activityManager.create(object);
    }

    @Override
    public Activity update(Activity object) {
        return null;
    }

    @Override
    @DeleteMapping("/deleteActivity/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return activityManager.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    @PostMapping("/createNewWithAdmin")
    public Activity createActivityWithAdmin(@RequestBody Activity activity) throws IdConflictException, EntityNotFoundException {
        return activityManager.createActivityWithAdminEmail(activity);
    }
}
