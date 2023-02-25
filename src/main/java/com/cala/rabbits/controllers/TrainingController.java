package com.cala.rabbits.controllers;

import com.cala.rabbits.models.dto.TrainingDTO;
import com.cala.rabbits.services.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

  private final TrainingService trainingService;

  public TrainingController(TrainingService trainingService) {
    this.trainingService = trainingService;
  }

  @GetMapping("/trainings")
  public ResponseEntity findTrainings(){
    return ResponseEntity.ok().body(trainingService.findallDTO());
  }

  @PostMapping("/trainings")
  public ResponseEntity addTraining(@RequestBody TrainingDTO trainingDTO){
    if (trainingDTO==null){
      return ResponseEntity.noContent().build();
    }
    trainingService.addTraining(trainingDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(trainingDTO);
  }

  @GetMapping("/trainings/{id}")
  public ResponseEntity findTrainingById(@PathVariable Long id){
    if (id == null){
      return ResponseEntity.noContent().build();
    }else if(!trainingService.existsTrainingById(id)){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(trainingService.findTrainingDtoById(id));
  }
  @PostMapping("/trainings/{id}")
  public ResponseEntity updateTrainingById(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
    if (id==null || trainingDTO == null){
      return ResponseEntity.noContent().build();
    }else if(!trainingService.existsTrainingById(id)){
      return ResponseEntity.notFound().build();
    }
    trainingService.updateTraining(id,trainingDTO);
    return ResponseEntity.ok().body(trainingDTO);
  }

  @DeleteMapping("/trainings/{id}")
  public ResponseEntity deleteTrainingById(@PathVariable Long id){
    if (id==null){
      return ResponseEntity.noContent().build();
    }else if (!trainingService.existsTrainingById(id)){
      return ResponseEntity.notFound().build();
    }
    TrainingDTO deletedTraining = trainingService.deleteTrainingById(id);
    return ResponseEntity.ok().body(deletedTraining);
  }
}
