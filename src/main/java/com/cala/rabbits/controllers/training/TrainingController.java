package com.cala.rabbits.controllers.training;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.RequestBodyMissingException;
import com.cala.rabbits.models.training.Training;
import com.cala.rabbits.models.training.dto.TrainingDTO;
import com.cala.rabbits.services.training.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @PostMapping("update/{trainingId}")
  public ResponseEntity updatedTraining(@PathVariable Long trainingId,@RequestBody TrainingDTO trainingDTO){
    if (trainingId == null || trainingId < 1){
      throw new InvalidIdException();
    }
    if (trainingDTO==null){
      throw new RequestBodyMissingException();
    }
    trainingService.updateTraining(trainingId,trainingDTO);
    return ResponseEntity.ok().body(trainingDTO);
  }

  @PostMapping("delete/{trainingId}")
  public ResponseEntity updatedTraining(@PathVariable Long trainingId){
    if (trainingId == null || trainingId < 1){
      throw new InvalidIdException();
    }
    trainingService.deleteTraining(trainingId);
    return ResponseEntity.status(202).build();
  }
}
