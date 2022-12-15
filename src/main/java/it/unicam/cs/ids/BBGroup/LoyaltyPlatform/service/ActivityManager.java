package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;

public interface ActivityManager extends EntityManager<Activity, Long>{

    Activity createActivityWithAdminEmail(Activity activity);
}
