package dev.knowhowto.jwtsecured.web;

import dev.knowhowto.jwtsecured.dto.AuthenticationRequest;
import dev.knowhowto.jwtsecured.dto.AuthenticationResponse;
import dev.knowhowto.jwtsecured.dto.RegisterRequest;
import dev.knowhowto.jwtsecured.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
