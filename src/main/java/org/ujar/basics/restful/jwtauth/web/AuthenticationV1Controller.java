package org.ujar.basics.restful.jwtauth.web;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.jwtauth.dto.AuthenticationRequestDto;
import org.ujar.basics.restful.jwtauth.entity.User;
import org.ujar.basics.restful.jwtauth.security.jwt.JwtTokenProvider;
import org.ujar.basics.restful.jwtauth.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationV1Controller {
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;


  @PostMapping("login")
  public ResponseEntity<Map<String, String>> login(@RequestBody final AuthenticationRequestDto requestDto) {
    try {
      String username = requestDto.getUsername();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
      User user = userService.findByUsername(username);

      if (user == null) {
        throw new UsernameNotFoundException("User with username: " + username + " not found");
      }

      String token = jwtTokenProvider.createToken(username, user.getRoles());

      var response = new HashMap<String, String>();
      response.put("username", username);
      response.put("token", token);

      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
