package com.cala.rabbits.config;

import com.cala.rabbits.models.AuthenticationRequest;
import com.cala.rabbits.models.AuthenticationResponse;
import com.cala.rabbits.models.RegisterRequest;
import com.cala.rabbits.models.Role;
import com.cala.rabbits.models.User;
import com.cala.rabbits.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(request.getPassword())
        .role(Role.Member)
        .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }


  public AuthenticationResponse login(AuthenticationRequest request) {
    return null;
  }
}
