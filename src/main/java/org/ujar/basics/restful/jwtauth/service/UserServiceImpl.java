package org.ujar.basics.restful.jwtauth.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ujar.basics.restful.jwtauth.entity.Role;
import org.ujar.basics.restful.jwtauth.entity.Status;
import org.ujar.basics.restful.jwtauth.entity.User;
import org.ujar.basics.restful.jwtauth.repository.RoleRepository;
import org.ujar.basics.restful.jwtauth.repository.UserRepository;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public User register(final User user) {
    final var roleUser = roleRepository.findByName("ROLE_USER");
    final var userRoles = new ArrayList<Role>();
    userRoles.add(roleUser);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(userRoles);
    user.setStatus(Status.ACTIVE);

    User registeredUser = userRepository.save(user);

    log.info("IN register - user: {} successfully registered", registeredUser);

    return registeredUser;
  }

  @Override
  public List<User> getAll() {
    final var result = userRepository.findAll();
    log.info("IN getAll - {} users found", result.size());
    return result;
  }

  @Override
  public User findByUsername(final String username) {
    final var result = userRepository.findByUsername(username);
    log.info("IN findByUsername - user: {} found by username: {}", result, username);
    return result;
  }

  @Override
  public User findById(final Long id) {
    final var result = userRepository.findById(id).orElse(null);

    if (result == null) {
      log.warn("IN findById - no user found by id: {}", id);
      return null;
    }

    log.info("IN findById - user: {} found by id: {}", result, id);
    return result;
  }

  @Override
  public void delete(final Long id) {
    userRepository.deleteById(id);
    log.info("IN delete - user with id: {} successfully deleted", id);
  }
}
