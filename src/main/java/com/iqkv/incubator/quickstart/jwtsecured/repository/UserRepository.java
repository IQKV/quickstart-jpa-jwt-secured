package com.iqkv.incubator.quickstart.jwtsecured.repository;

import java.util.Optional;

import com.iqkv.incubator.quickstart.jwtsecured.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
