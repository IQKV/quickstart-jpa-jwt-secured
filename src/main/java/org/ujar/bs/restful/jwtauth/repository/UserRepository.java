package org.ujar.bs.rst.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.jwtauth.entity.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}
