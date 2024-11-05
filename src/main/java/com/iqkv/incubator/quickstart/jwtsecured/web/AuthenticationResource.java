package com.iqkv.incubator.quickstart.jwtsecured.web;

import com.iqkv.incubator.quickstart.jwtsecured.dto.AuthenticationRequest;
import com.iqkv.incubator.quickstart.jwtsecured.dto.AuthenticationResponse;
import com.iqkv.incubator.quickstart.jwtsecured.dto.RegisterRequest;
import com.iqkv.incubator.quickstart.jwtsecured.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
record AuthenticationResource(AuthenticationService service) {

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
