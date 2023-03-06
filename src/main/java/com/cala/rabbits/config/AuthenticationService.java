package com.cala.rabbits.config;

import com.cala.rabbits.models.user.User;
import com.cala.rabbits.models.user.dto.AuthenticationRequest;
import com.cala.rabbits.models.user.dto.AuthenticationResponse;
import com.cala.rabbits.models.user.dto.RegisterRequest;
import com.cala.rabbits.repositories.user.UserRepository;
import com.cala.rabbits.services.EmailService;
import java.time.LocalDate;
import java.util.UUID;
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
        .enabled(false)
        .emailVerificationToken(UUID.randomUUID().toString())
        .build();
    userRepository.save(user);
    emailService.sendSimpleMessage(request.getEmail(),"Register","Thenk you for registering!\n "
        + "We would like to ask you to verify your email address with the following link:"
        + " http://localhost:8080/user/email/verification/"+ user.getEmailVerificationToken());
    return AuthenticationResponse.builder().build();
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


  public AuthenticationResponse emailVerification(AuthenticationRequest request, String emailVerificationToken) {
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
    if (!user.getEmailVerificationToken().equals(emailVerificationToken)){
      throw new RuntimeException("Wrong email verification token!");
    }
    user.setEmailVerificationToken(null);
    user.setEnabled(true);
    user.setVerifiedAt(LocalDate.now().toString());
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build() ;
  }
}
