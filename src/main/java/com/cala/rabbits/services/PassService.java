package com.cala.rabbits.services;

import com.cala.rabbits.models.dto.PassCreationRequest;
import com.cala.rabbits.models.dto.PassUpdateRequest;

public interface PassService {

  void createNewPass(PassCreationRequest request);

  void deletePassById(Long id);

  void updatePassById(Long id, PassUpdateRequest request);
}
