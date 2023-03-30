package org.ujar.jwtsecured.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.jwtsecured.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
