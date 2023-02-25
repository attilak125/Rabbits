package com.cala.rabbits.services;

import com.cala.rabbits.models.dto.TrainingDTO;
import java.util.List;

public interface TrainingService {

  List<TrainingDTO> findallDTO();
}
