package com.cala.rabbits.controllers.training;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.RequestBodyMissingException;
import com.cala.rabbits.models.training.dto.TrainingDTO;
import com.cala.rabbits.services.training.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session/training")
public class TrainingController {

  private final TrainingService trainingService;

  public TrainingController(TrainingService trainingService) {
    this.trainingService = trainingService;
  }

  @GetMapping("/{sessionId}")
  public ResponseEntity findAllTrainings(@PathVariable Long sessionId){
    if (sessionId == null || sessionId < 1){
      throw new InvalidIdException();
    }
    return ResponseEntity.ok().body(trainingService.findTrainingForSessionDto(sessionId));
  }

  @PostMapping("/{sessionId}")
  public ResponseEntity addTraining(@PathVariable Long sessionId,@RequestBody TrainingDTO trainingDTO){
    if (sessionId == null || sessionId < 1){
      throw new InvalidIdException();
    }
    if (trainingDTO==null){
      throw new RequestBodyMissingException();
    }
    trainingService.addTraining(sessionId,trainingDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(trainingDTO);
  }
//
//  @GetMapping("/by/{type}")
//  public ResponseEntity findTrainingsByType(@PathVariable String type){
//    if (type == null){
//      throw new PathVariableMissingException();
//    }
//    return ResponseEntity.ok().body(trainingService.findTrainingsDtoByType(type));
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity findTrainingById(@PathVariable Long id){
//    if (id < 0 || !trainingService.existsTrainingById(id)){
//      throw new InvalidIdException();
//    }
//    return ResponseEntity.ok().body(trainingService.findTrainingDtoById(id));
//  }
//  @PostMapping("/{id}")
//  public ResponseEntity updateTrainingById(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
//    if (id < 0 || !trainingService.existsTrainingById(id)){
//      throw new InvalidIdException();
//    } else if(trainingDTO == null){
//      throw new RequestBodyMissingException();
//    }
//    trainingService.updateTraining(id,trainingDTO);
//    return ResponseEntity.ok().body(trainingDTO);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity deleteTrainingById(@PathVariable Long id){
//    if (id < 0 || !trainingService.existsTrainingById(id)){
//      throw new InvalidIdException();
//    }
//    TrainingDTO deletedTraining = trainingService.deleteTrainingById(id);
//    return ResponseEntity.status(202).body(deletedTraining);
//  }
//
//  @PostMapping("/join/{id}")
//  public ResponseEntity joinTraining(@PathVariable Long id,@RequestBody JoinTrainingRequest request){
//    if (id < 0 || !trainingService.existsTrainingById(id)){
//      throw new InvalidIdException();
//    }
//    trainingService.joinTrainingById(id,request);
//    return ResponseEntity.status(HttpStatus.CREATED).build();
//  }
}
