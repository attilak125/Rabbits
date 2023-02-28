package com.cala.rabbits.config;

import com.cala.rabbits.models.dto.AuthenticationRequest;
import com.cala.rabbits.models.dto.AuthenticationResponse;
import com.cala.rabbits.models.dto.RegisterRequest;
import com.cala.rabbits.models.User;
import com.cala.rabbits.repositories.UserRepository;
import com.cala.rabbits.services.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EmailService emailService;

  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService, AuthenticationManager authenticationManager, EmailService emailService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.emailService = emailService;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(request.getPassword())
        .role(request.getRole())
        .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    emailService.sendSimpleMessage(request.getEmail(),"Register","thank you for registering.");
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse login(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
