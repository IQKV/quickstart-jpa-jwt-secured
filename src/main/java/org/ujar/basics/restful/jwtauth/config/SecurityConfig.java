package org.ujar.basics.restful.jwtauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.ujar.basics.restful.jwtauth.security.jwt.JwtConfigurer;
import org.ujar.basics.restful.jwtauth.security.jwt.JwtTokenProvider;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
@RequiredArgsConstructor
class SecurityConfig  extends WebSecurityConfigurerAdapter {

  private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
  private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  @Override
  AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement(
            customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeRequests(authorizeRequests -> {
          authorizeRequests.antMatchers(LOGIN_ENDPOINT).permitAll();
          authorizeRequests.antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN");
          authorizeRequests.anyRequest().authenticated();
        })
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}
