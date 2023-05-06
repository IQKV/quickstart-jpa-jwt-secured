package org.ujar.jwtsecured.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.jwtsecured.dto.AuthenticationRequest;
import org.ujar.jwtsecured.dto.AuthenticationResponse;
import org.ujar.jwtsecured.dto.RegisterRequest;
import org.ujar.jwtsecured.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
record AuthenticationResource(AuthenticationService service)  {

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
