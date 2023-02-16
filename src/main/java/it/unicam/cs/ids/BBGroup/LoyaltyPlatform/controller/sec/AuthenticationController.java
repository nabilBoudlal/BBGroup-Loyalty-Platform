package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller.sec;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.auth.AuthenticationRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.auth.AuthenticationResponse;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.auth.AuthenticationService;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
