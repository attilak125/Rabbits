package com.cala.rabbits.services;

import com.cala.rabbits.models.dto.TrainingDTO;
import java.util.List;

public interface TrainingService {

  List<TrainingDTO> findallDTO();

  void addTraining(TrainingDTO trainingDTO);

  TrainingDTO findTrainingDtoById(long id);

  void updateTraining(long id, TrainingDTO trainingDTO);

  boolean existsTrainingById(long id);

  TrainingDTO deleteTrainingById(long id);
}
