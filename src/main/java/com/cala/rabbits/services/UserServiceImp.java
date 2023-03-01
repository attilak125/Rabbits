package com.cala.rabbits.services;

import com.cala.rabbits.models.Training;
import com.cala.rabbits.repositories.TrainingRepository;
import com.cala.rabbits.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

  private final EmailService emailService;
  private final UserRepository userRepository;
  private final TrainingService trainingservice;

  public UserServiceImp(EmailService emailService, UserRepository userRepository,
      TrainingService trainingservice) {
    this.emailService = emailService;
    this.userRepository = userRepository;
    this.trainingservice = trainingservice;
  }

  @Scheduled(cron = "0 24 9 * * ?")
  private void dailyEmail(){
    List<String> emailList = userRepository.findAll().stream().map(user -> user.getEmail()).toList();
    Optional<Training> todayTraining = trainingservice.findTrainingByDoDate(LocalDate.now());
    for (String email : emailList) {
        emailService.sendSimpleMessage(email,"Daily training", todayTraining.isPresent()?todayTraining.get().getExercises():"Please ask the trainer about is unfortunately it is not uploaded yet");
    }
  }

}
