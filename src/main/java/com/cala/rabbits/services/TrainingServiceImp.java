package com.cala.rabbits.services;

import com.cala.rabbits.models.Training;
import com.cala.rabbits.models.dto.TrainingDTO;
import com.cala.rabbits.repositories.TrainingRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImp implements TrainingService{
  private final TrainingRepository trainingRepository;

  public TrainingServiceImp(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  @Override
  public List<TrainingDTO> findallDTO() {
    return trainingRepository.findAll().stream().map(this::trainingConvertToDTO);
  }

  private TrainingDTO trainingConvertToDTO(Training training){
    return new TrainingDTO(training.getType(),training.getCreationDate(),training.getExercises());
  }
}
