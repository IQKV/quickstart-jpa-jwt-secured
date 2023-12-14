package dev.knowhowto.jwtsecured.repository;

import java.util.Optional;

import dev.knowhowto.jwtsecured.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
