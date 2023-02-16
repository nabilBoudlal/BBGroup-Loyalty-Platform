package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

}