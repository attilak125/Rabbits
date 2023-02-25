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
    return trainingRepository.findAll().stream().map(this::trainingConvertToDTO).toList();
  }

  @Override
  public void addTraining(TrainingDTO trainingDTO) {
    trainingRepository.save(new Training(trainingDTO.getType(),trainingDTO.getCreationDate(),trainingDTO.getExercises()));
  }

  @Override
  public TrainingDTO findTrainingDtoById(long id) {
    return trainingConvertToDTO(trainingRepository.findById(id).orElseThrow());
  }

  @Override
  public boolean existsTrainingById(long id) {
    return trainingRepository.existsById(id);
  }

  @Override
  public TrainingDTO deleteTrainingById(long id) {
    Training deletedTraining = trainingRepository.findById(id).orElseThrow();
    trainingRepository.deleteById(id);
    return trainingConvertToDTO(deletedTraining);
  }

  @Override
  public void updateTraining(long id, TrainingDTO trainingDTO) {
    Training training = trainingRepository.findById(id).orElseThrow();
    training.setType(trainingDTO.getType());
    training.setCreationDate(trainingDTO.getCreationDate());
    training.setExercises(trainingDTO.getExercises());
    trainingRepository.save(training);
  }

  private TrainingDTO trainingConvertToDTO(Training training){
    return new TrainingDTO(training.getType(),training.getCreationDate(),training.getExercises());
  }
}
