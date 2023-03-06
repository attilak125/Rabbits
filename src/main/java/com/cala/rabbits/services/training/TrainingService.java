package com.cala.rabbits.services.training;

import com.cala.rabbits.models.training.dto.TrainingDTO;

public interface TrainingService {

  TrainingDTO findTrainingForSessionDto(Long sessionId);

  void addTraining(Long sessionId,TrainingDTO trainingDTO);

  void updateTraining(Long trainingId, TrainingDTO trainingDTO);

  void deleteTraining(Long trainingId);
}
