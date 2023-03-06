package com.cala.rabbits.services.user;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.UserNotFoundException;
import com.cala.rabbits.models.user.Pass;
import com.cala.rabbits.models.user.User;
import com.cala.rabbits.models.training.dto.PassCreationRequest;
import com.cala.rabbits.models.training.dto.PassUpdateRequest;
import com.cala.rabbits.repositories.user.PassRepository;
import com.cala.rabbits.repositories.user.UserRepository;
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
    passRepository.save(pass);
    userRepository.save(user);
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
    }
    Pass pass = passRepository.findById(id).get();
    pass.setCreationDate(request.getCreationDate());
    passRepository.save(pass);
  }
}
