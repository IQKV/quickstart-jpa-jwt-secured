package org.ujar.basics.restful.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.jwtauth.entity.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}
