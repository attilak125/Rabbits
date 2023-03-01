package com.cala.rabbits.services;

import com.cala.rabbits.models.Training;
import com.cala.rabbits.models.dto.TrainingDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingService {

  List<TrainingDTO> findallDto();

  void addTraining(TrainingDTO trainingDTO);

  TrainingDTO findTrainingDtoById(long id);

  void updateTraining(long id, TrainingDTO trainingDTO);

  boolean existsTrainingById(long id);

  TrainingDTO deleteTrainingById(long id);

  List<TrainingDTO> findTrainingsDtoByType(String type);

  Optional<Training> findTrainingByDoDate(LocalDate doDate);
}
