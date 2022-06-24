package org.ujar.basics.restful.jwtauth.service;

import java.util.List;
import org.ujar.basics.restful.jwtauth.entity.User;

/**
 * Service interface for class {@link User}.
 */
public interface UserService {

  User register(User user);

  List<User> getAll();

  User findByUsername(String username);

  User findById(Long id);

  void delete(Long id);
}
