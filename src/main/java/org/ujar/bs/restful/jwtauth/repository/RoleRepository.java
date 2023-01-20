package org.ujar.bs.rst.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.jwtauth.entity.Role;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
