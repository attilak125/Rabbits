package com.cala.rabbits.services;

import com.cala.rabbits.models.Training;
import com.cala.rabbits.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImp implements TrainingService{
  private final TrainingRepository trainingRepository;

  public TrainingServiceImp(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

}
