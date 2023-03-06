package com.cala.rabbits.controllers.training;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.models.training.dto.JoinSessionRequest;
import com.cala.rabbits.models.training.dto.RemoveFromSessionRequest;
import com.cala.rabbits.models.training.dto.SessionsDTO;
import com.cala.rabbits.services.training.SessionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

  private final SessionService sessionService;

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @GetMapping
  public ResponseEntity<List<SessionsDTO>> findAllSessionCurrentWeek(){
    return ResponseEntity.ok().body(sessionService.findAllSessionCurrentWeek());
  }
  @PostMapping("/{id}")
  public ResponseEntity<SessionsDTO> joinSession(@PathVariable Long id, @RequestBody JoinSessionRequest request){
    if (id < 1){
      throw new InvalidIdException();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.joinSession(id,request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity removeParticipantFromSession(@PathVariable Long id,@RequestBody
      RemoveFromSessionRequest request){
    if (id < 1){
      throw new InvalidIdException();
    }
    sessionService.removeParticipantFromSession(id,request);
    return ResponseEntity.status(202).build();
  }
  @PostMapping
  public ResponseEntity createSessionForToday(){
    sessionService.createSession();
    return ResponseEntity.ok().build();
  }
}
