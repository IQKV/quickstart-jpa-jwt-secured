package org.ujar.bs.sec.jwt.config;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("ujar.jwt.token")
public record JwtTokenProperties(@NotNull String secret, @NotNull Long expiration) {

  public String getSecret() {
    return secret();
  }

  public Long getExpiration() {
    return expiration();
  }
}
