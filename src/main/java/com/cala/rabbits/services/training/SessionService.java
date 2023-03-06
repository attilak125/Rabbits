package com.cala.rabbits.services.training;

import com.cala.rabbits.models.training.dto.JoinSessionRequest;
import com.cala.rabbits.models.training.dto.RemoveFromSessionRequest;
import com.cala.rabbits.models.training.dto.SessionsDTO;
import java.util.List;

public interface SessionService {

  SessionsDTO joinSession(Long id, JoinSessionRequest request);

  List<SessionsDTO> findAllSessionCurrentWeek();

  void removeParticipantFromSession(Long id, RemoveFromSessionRequest request);
}
