package org.ujar.basics.restful.jwtauth.service;

import java.util.List;
import org.ujar.basics.restful.jwtauth.entity.User;

/**
 * Service interface for class {@link User}.
 */
public interface UserService {

  User register(final User user);

  List<User> getAll();

  User findByUsername(final String username);

  User findById (final Long id);

  void delete(final Long id);
}
