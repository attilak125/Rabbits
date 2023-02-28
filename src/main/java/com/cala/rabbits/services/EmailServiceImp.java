package com.cala.rabbits.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImp implements EmailService{

  private final JavaMailSender emailSender;

  public EmailServiceImp(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(to);
    email.setSubject(subject);
    email.setText(text);
    emailSender.send(email);
  }
}
