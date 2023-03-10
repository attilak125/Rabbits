package com.cala.rabbits.controllers.user;

import com.cala.rabbits.config.AuthenticationService;
import com.cala.rabbits.models.user.dto.AuthenticationRequest;
import com.cala.rabbits.models.user.dto.AuthenticationResponse;
import com.cala.rabbits.models.user.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final AuthenticationService authenticationService;

  public UserController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
      return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/email/verification/{emailVerificationToken}")
  public ResponseEntity<AuthenticationResponse> emailVerification(@RequestBody AuthenticationRequest request, @PathVariable String emailVerificationToken){
    return ResponseEntity.ok(authenticationService.emailVerification(request, emailVerificationToken));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
      return ResponseEntity.ok(authenticationService.login(request));
  }

}
