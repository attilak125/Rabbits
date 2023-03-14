package com.cala.rabbits.services.user;

import com.cala.rabbits.models.training.dto.PassCreationRequest;
import com.cala.rabbits.models.training.dto.PassDTO;
import com.cala.rabbits.models.training.dto.PassUpdateRequest;

public interface PassService {

  void createNewPass(PassCreationRequest request);

  void deletePassById(Long id);

  void updatePassById(Long id, PassUpdateRequest request);

  PassDTO findPassById(Long id);
}
