package com.cala.rabbits.services.training;

import com.cala.rabbits.repositories.training.TrainingRepository;
import com.cala.rabbits.repositories.training.WodRepository;
import com.cala.rabbits.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImp implements TrainingService{
  private final TrainingRepository trainingRepository;
  private final WodRepository wodRepository;

  public TrainingServiceImp(TrainingRepository trainingRepository, WodRepository wodRepository) {
    this.trainingRepository = trainingRepository;
    this.wodRepository = wodRepository;
  }
  //  @Override
//  public List<TrainingDTO> findallDto() {
//    return trainingRepository.findAll().stream().map(this::trainingConvertToDTO).toList();
//  }
//
//  @Override
//  public void addTraining(TrainingDTO trainingDTO) {
//    trainingRepository.save(new Training(trainingDTO.getType(),trainingDTO.getExercises()));
//  }
//
//  @Override
//  public TrainingDTO findTrainingDtoById(long id) {
//    return trainingConvertToDTO(trainingRepository.findById(id).orElseThrow());
//  }
//
//  @Override
//  public boolean existsTrainingById(long id) {
//    return trainingRepository.existsById(id);
//  }
//
//  @Override
//  public TrainingDTO deleteTrainingById(long id) {
//    Training deletedTraining = trainingRepository.findById(id).orElseThrow();
//    trainingRepository.deleteById(id);
//    return trainingConvertToDTO(deletedTraining);
//  }
//
//  @Override
//  public List<TrainingDTO> findTrainingsDtoByType(String type) {
//    return trainingRepository.findAllByType(type).stream().map(training -> trainingConvertToDTO(training)).toList();
//  }
//
//  @Override
//  public Optional<Training> findTrainingByDoDate(LocalDate doDate) {
//    return trainingRepository.findByDoDate(doDate);
//  }
//
//  @Override
//  public List<TrainingDTO> findCurrentWeeksTrainingsDTO() {
//    return null;
//  }
//
//  @Override
//  public void joinTrainingById(Long id, JoinTrainingRequest request) {
//    if (!userRepository.existsByEmail(request.getEmail())){
//      throw new UserNotFoundException();
//    }
//    Training training = trainingRepository.findById(id).get();
//    training.addParticipants(userRepository.findByEmail(request.getEmail()).get());
//    trainingRepository.save(training);
//  }
//
//  @Override
//  public void updateTraining(long id, TrainingDTO trainingDTO) {
//    Training training = trainingRepository.findById(id).orElseThrow();
//    training.setType(trainingDTO.getType());
//    training.setExercises(trainingDTO.getExercises());
//    trainingRepository.save(training);
//  }
//
//  private TrainingDTO trainingConvertToDTO(Training training){
//    return new TrainingDTO(training.getType(),training.getExercises());
//  }
}
