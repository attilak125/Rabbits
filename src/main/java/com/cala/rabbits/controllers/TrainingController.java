package com.cala.rabbits.controllers;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.PathVariableMissingException;
import com.cala.rabbits.exception.RequestBodyMissingException;
import com.cala.rabbits.models.dto.TrainingDTO;
import com.cala.rabbits.services.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

  private final TrainingService trainingService;

  public TrainingController(TrainingService trainingService) {
    this.trainingService = trainingService;
  }

  @GetMapping("/trainings")
  public ResponseEntity findTrainings(){
    return ResponseEntity.ok().body(trainingService.findallDto());
  }

  @PostMapping("/trainings")
  public ResponseEntity addTraining(@RequestBody TrainingDTO trainingDTO){
    if (trainingDTO==null){
      throw new RequestBodyMissingException();
    }
    trainingService.addTraining(trainingDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(trainingDTO);
  }

  @GetMapping("/trainings/by/type/{type}")
  public ResponseEntity findTrainingsByType(@PathVariable String type){
    if (type == null){
      throw new PathVariableMissingException();
    }
    return ResponseEntity.ok().body(trainingService.findTrainingsDtoByType(type));
  }

  @GetMapping("/trainings/{id}")
  public ResponseEntity findTrainingById(@PathVariable Long id){
    if (id < 0 || !trainingService.existsTrainingById(id)){
      throw new InvalidIdException();
    }
    return ResponseEntity.ok().body(trainingService.findTrainingDtoById(id));
  }
  @PostMapping("/trainings/{id}")
  public ResponseEntity updateTrainingById(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
    if (id < 0 || !trainingService.existsTrainingById(id)){
      throw new InvalidIdException();
    } else if(trainingDTO == null){
      throw new RequestBodyMissingException();
    }
    trainingService.updateTraining(id,trainingDTO);
    return ResponseEntity.ok().body(trainingDTO);
  }

  @DeleteMapping("/trainings/{id}")
  public ResponseEntity deleteTrainingById(@PathVariable Long id){
    if (id < 0 || !trainingService.existsTrainingById(id)){
      throw new InvalidIdException();
    }
    TrainingDTO deletedTraining = trainingService.deleteTrainingById(id);
    return ResponseEntity.ok().body(deletedTraining);
  }
}
