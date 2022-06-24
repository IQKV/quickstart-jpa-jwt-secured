package org.ujar.basics.restful.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.jwtauth.entity.Role;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
