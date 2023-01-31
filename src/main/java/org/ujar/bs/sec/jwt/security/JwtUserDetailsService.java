package org.ujar.bs.sec.jwt.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ujar.bs.sec.jwt.entity.User;
import org.ujar.bs.sec.jwt.security.jwt.JwtUser;
import org.ujar.bs.sec.jwt.security.jwt.JwtUserFactory;
import org.ujar.bs.sec.jwt.service.UserService;

/**
 * Implementation of {@link UserDetailsService} interface for {@link JwtUser}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    User user = userService.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User with username: " + username + " not found");
    }

    JwtUser jwtUser = JwtUserFactory.create(user);
    log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
    return jwtUser;
  }
}
