package com.cala.rabbits.services;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.UserNotFoundException;
import com.cala.rabbits.models.Pass;
import com.cala.rabbits.models.User;
import com.cala.rabbits.models.dto.PassCreationRequest;
import com.cala.rabbits.models.dto.PassUpdateRequest;
import com.cala.rabbits.repositories.PassRepository;
import com.cala.rabbits.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PassServiceImp implements PassService{

  private final PassRepository passRepository;
  private final UserRepository userRepository;

  public PassServiceImp(PassRepository passRepository, UserRepository userRepository) {
    this.passRepository = passRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void createNewPass(PassCreationRequest request) {
    User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
    Pass pass = new Pass(request.getPassType(), request.getCreationDate(),user);
    user.setPass(pass);
    userRepository.save(user);
    passRepository.save(pass);
  }

  @Override
  public void deletePassById(Long id) {
    if(!passRepository.existsById(id)){
      throw new InvalidIdException();
    }
    Pass pass = passRepository.findById(id).get();
    if(!userRepository.existsById(pass.getUser().getId())){
      throw new UserNotFoundException();
    }
    userRepository.deleteById(passRepository.findById(id).get().getUser().getId());
    passRepository.deleteById(id);
  }

  @Override
  public void updatePassById(Long id, PassUpdateRequest request) {
    if(!passRepository.existsById(id)){
      throw new InvalidIdException();
    }else if(userRepository.existsByEmail(request.getEmail())){
      throw new UserNotFoundException();
    }
    Pass pass = passRepository.findById(id).get();
    pass.setCreationDate(request.getCreationDate());
    passRepository.save(pass);
  }
}
