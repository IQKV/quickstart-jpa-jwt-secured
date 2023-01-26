package org.ujar.bs.jwt.auth.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.ujar.bs.jwt.auth.entity.Role;
import org.ujar.bs.jwt.auth.entity.Status;
import org.ujar.bs.jwt.auth.entity.User;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 */
@NoArgsConstructor
public final class JwtUserFactory {

  public static JwtUser create(User user) {
    return new JwtUser(
        user.getId(),
        user.getUsername(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getPassword(),
        mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
        user.getStatus().equals(Status.ACTIVE),
        user.getUpdated()
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
    return userRoles.stream()
        .map(role ->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
  }
}
