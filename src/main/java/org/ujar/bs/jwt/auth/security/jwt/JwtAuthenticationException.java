package org.ujar.bs.jwt.auth.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

  public JwtAuthenticationException(String msg) {
    super(msg);
  }
}
