package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.*;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityJoinRequestRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerJoinRequestRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.EmployeeJoinRequestRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/RequestHandler")
public class JoinRequestHandler {

    @Autowired
    private PlatformAdminManager adminManager;
    @Autowired
    private ActivityJoinRequestManager requestManager;

    @Autowired
    private CostumerJoinRequestManager costumerJoinRequestManager;

    @Autowired
    private EmployeeJoinRequestManager employeeJoinRequestManager;

    @Autowired
    private ActivityJoinRequestRepository activityJoinRequestRepository;

    @Autowired
    private CostumerJoinRequestRepository costumerJoinRequestRepository;

    @Autowired
    private EmployeeJoinRequestRepository employeeJoinRequestRepository;
    @Autowired
    private ActivityManager activityManager;

    @Autowired
    private CostumerManager costumerManager;

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping("/listActivity")
    public Iterable<ActivityJoinRequest> getActivitiesRequests(){
        return activityJoinRequestRepository.findAll();
    }

    @GetMapping("/listCostumer")
    public Iterable<CostumerJoinRequest> getCostumersRequests() {
        return costumerJoinRequestRepository.findAll();
    }

    @GetMapping("/listEmployee")
    public Iterable<EmployeeJoinRequest> getEmployeeRequests() {
        return employeeJoinRequestRepository.findAll();
    }

    @GetMapping("/checkActivity/{requestId}")
    public ActivityJoinRequest checkRequestActivity(@PathVariable Long requestId) throws EntityNotFoundException {
       return activityJoinRequestRepository.findById(requestId).orElseThrow();
    }

    @GetMapping("/checkCostumer/{requestId}")
    public CostumerJoinRequest checkRequestCostumer(@PathVariable Long requestId) throws EntityNotFoundException {
        return costumerJoinRequestRepository.findById(requestId).orElseThrow();
    }

    @GetMapping("/checkEmployee/{requestId}")
    public EmployeeJoinRequest checkRequestEmployee(@PathVariable Long requestId) throws EntityNotFoundException {
        return employeeJoinRequestRepository.findById(requestId).orElseThrow();
    }

    /**
     * > This function validates a request to create a new activity, and if the request is valid, it creates the activity
     *
     * @param requestId The id of the request to be validated.
     * @return Activity
     */
    @PostMapping("/validateRequestActivity/{requestId}")
    public Activity validateRequestActivity(@PathVariable Long requestId) throws EntityNotFoundException, IdConflictException {
        ActivityJoinRequest request = requestManager.getInstance(requestId);
        Activity activity= new Activity( request.getActivityName(), request.getActivityEmail(), request.getVatCode(), request.getAddress(), request.getPhone());
        request.validate();
        return activityManager.create(activity);
    }

    @PostMapping("validateRequestCostumer/{costumerId}")
    public Costumer valideRequestCostumer(@PathVariable Long costumerId) throws EntityNotFoundException, IdConflictException {
        CostumerJoinRequest request = costumerJoinRequestManager.getInstance(costumerId);
        Costumer costumer = new Costumer(request.getCostumerEmail(), request.getCostumerName(), request.getCostumerSurname(), request.getAddress(), request.getPhone());
        request.validate();
        return costumerManager.create(costumer);
    }

    @PostMapping("validateRequestEmployee/{employeeId}")
    public Employee validateRequestEmployee(@PathVariable Long employeeId) throws EntityNotFoundException, IdConflictException {
        EmployeeJoinRequest request = employeeJoinRequestManager.getInstance(employeeId);
        Employee employee = new Employee(request.getEmployeeEmail(), request.getEmployeeName(), request.getEmployeeSurname(), request.getAddress(), request.getPhone());
        request.validate();
        return employeeManager.create(employee);
    }


}
