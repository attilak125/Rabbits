package com.cala.rabbits.controllers.user;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.models.training.dto.PassDTO;
import com.cala.rabbits.models.user.Pass;
import com.cala.rabbits.models.training.dto.PassCreationRequest;
import com.cala.rabbits.models.training.dto.PassUpdateRequest;
import com.cala.rabbits.services.user.PassService;
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
@RequestMapping("/pass")
public class PassController {

  private final PassService passService;

  public PassController(PassService passService) {
    this.passService = passService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PassDTO> getPass(@PathVariable Long id) {
    if (id<=0){
      throw new InvalidIdException();
    }
    return ResponseEntity.ok().body(passService.findPassById(id));
  }

  @PostMapping
  public ResponseEntity<Pass> createPass(@RequestBody PassCreationRequest request){
    passService.createNewPass(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{id}")
  public ResponseEntity<Pass> updatedPassById(@PathVariable Long id,@RequestBody PassUpdateRequest request){
    if (id<=0){
      throw new InvalidIdException();
    }
    passService.updatePassById(id,request);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Pass> deletePassById(@PathVariable Long id){
    if (id<=0){
      throw new InvalidIdException();
    }
    passService.deletePassById(id);
    return ResponseEntity.status(202).build();
  }
}
