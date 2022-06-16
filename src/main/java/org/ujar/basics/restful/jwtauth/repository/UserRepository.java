package org.ujar.basics.restful.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.jwtauth.model.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}
