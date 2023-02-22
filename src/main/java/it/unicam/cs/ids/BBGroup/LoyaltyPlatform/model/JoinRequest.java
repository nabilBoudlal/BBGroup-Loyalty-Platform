package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

/*
 * This interface represent a join request, which is also an entity in the db
 */

public interface JoinRequest {

    /**
     * It validates the joining request to the platform.
     */
    void validate();
}
