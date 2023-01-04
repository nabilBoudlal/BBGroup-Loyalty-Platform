package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityJoinRequestRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityJoinRequestManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.PlatformAdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/RequestHandler")
public class ActivityJoinRequestHandler {

    @Autowired
    private PlatformAdminManager adminManager;
    @Autowired
    private ActivityJoinRequestManager requestManager;

    @Autowired
    private ActivityJoinRequestRepository activityJoinRequestRepository;
    @Autowired
    private ActivityManager activityManager;

    @GetMapping("/list")
    public Iterable<ActivityJoinRequest> getActivitiesRequests(){
        return activityJoinRequestRepository.findAll();
    }

    @GetMapping("/{requestId}")
    public ActivityJoinRequest checkRequest(@PathVariable Long requestId) throws EntityNotFoundException {
       return activityJoinRequestRepository.findById(requestId).orElseThrow();
    }

    /**
     * > This function validates a request to create a new activity, and if the request is valid, it creates the activity
     *
     * @param requestId The id of the request to be validated.
     * @return Activity
     */
    @PostMapping("/validateRequest/{requestId}")
    public Activity validateRequest(@PathVariable Long requestId) throws EntityNotFoundException, IdConflictException {
        ActivityJoinRequest request = requestManager.getInstance(requestId);
        Activity activity= new Activity( request.getActivityName(), request.getActivityEmail(), request.getVatCode(), request.getAddress(), request.getPhone());
        return activityManager.create(activity);
    }


}
