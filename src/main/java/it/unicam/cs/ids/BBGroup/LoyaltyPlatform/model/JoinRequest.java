package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;

public interface JoinRequest {

    /**
     * It validates the joining request to the platform.
     */
    void validate();
}
