package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;

    private String phone;
    private String password;
}
