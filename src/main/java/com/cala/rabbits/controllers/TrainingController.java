package com.cala.rabbits.controllers;

import com.cala.rabbits.models.dto.TrainingDTO;
import com.cala.rabbits.services.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

  private final TrainingService trainingService;

  public TrainingController(TrainingService trainingService) {
    this.trainingService = trainingService;
  }

  @GetMapping("/trainings")
  public ResponseEntity findTrainings(){
    return ResponseEntity.ok().body(trainingService.findallDTO())
  }
}
