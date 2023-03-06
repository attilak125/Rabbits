package com.cala.rabbits.services.training;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.models.training.Session;
import com.cala.rabbits.models.training.Training;
import com.cala.rabbits.models.training.Wod;
import com.cala.rabbits.models.training.dto.TrainingDTO;
import com.cala.rabbits.models.training.dto.WodDTO;
import com.cala.rabbits.repositories.training.SessionRepository;
import com.cala.rabbits.repositories.training.TrainingRepository;
import com.cala.rabbits.repositories.training.WodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImp implements TrainingService{
  private final TrainingRepository trainingRepository;

  private final SessionRepository sessionRepository;
  private final WodRepository wodRepository;

  public TrainingServiceImp(TrainingRepository trainingRepository,
      SessionRepository sessionRepository, WodRepository wodRepository) {
    this.trainingRepository = trainingRepository;
    this.sessionRepository = sessionRepository;
    this.wodRepository = wodRepository;
  }

  @Override
  public TrainingDTO findTrainingForSessionDto(Long sessionId) {
    Optional<Session> optionalSession = sessionRepository.findById(sessionId);
    if (!optionalSession.isPresent()){
      throw new InvalidIdException();
    }
    Optional<Training> training = trainingRepository.findById(optionalSession.get().getTraining().getId());
    if (training.isPresent()){
      return trainingConvertToDTO(training.get());
    }
    return null;
  }

  @Override
  public void addTraining(Long sessionId, TrainingDTO trainingDTO) {
    Optional<Session> optionalSession = sessionRepository.findById(sessionId);
    if (!optionalSession.isPresent()){
      throw new InvalidIdException();
    }
    Training training = new Training(trainingDTO.getType());
    trainingRepository.save(training);
    List<Wod> wodList = trainingDTO.getExercises().stream().map(wod -> wodRepository.save(new Wod(wod.getExercises(),wod.getRounds(),training))).toList();
    optionalSession.get().setTraining(training);
    sessionRepository.save(optionalSession.get());
  }

  private TrainingDTO trainingConvertToDTO(Training training){
    return new TrainingDTO(training.getType(), training.getWods().stream().map(wod -> wodConvertToDto(wod)).toList());
  }

  private WodDTO wodConvertToDto(Wod wod){
    return new WodDTO(wod.getExercises(),wod.getRounds());
  }
}
