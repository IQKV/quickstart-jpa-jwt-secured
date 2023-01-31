package org.ujar.bs.sec.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.sec.jwt.entity.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}
