package com.cala.rabbits.services.training;

import com.cala.rabbits.exception.InvalidIdException;
import com.cala.rabbits.exception.UserNotFoundException;
import com.cala.rabbits.models.training.Session;
import com.cala.rabbits.models.training.dto.JoinSessionRequest;
import com.cala.rabbits.models.training.dto.RemoveFromSessionRequest;
import com.cala.rabbits.models.training.dto.SessionsDTO;
import com.cala.rabbits.models.user.User;
import com.cala.rabbits.repositories.training.SessionRepository;
import com.cala.rabbits.repositories.training.TrainingRepository;
import com.cala.rabbits.repositories.user.UserRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImp implements SessionService{

  private final SessionRepository sessionRepository;
  private final UserRepository userRepository;

  public SessionServiceImp(SessionRepository sessionRepository,
      UserRepository userRepository) {
    this.sessionRepository = sessionRepository;
    this.userRepository = userRepository;
  }

  @Override
  public SessionsDTO joinSession(Long id, JoinSessionRequest request) {
    Optional<Session> optionalSession = sessionRepository.findById(id);
    if (!optionalSession.isPresent()){
      throw new InvalidIdException();
    }
    Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
    if (!optionalUser.isPresent()){
      throw new UserNotFoundException();
    }
    User user = optionalUser.get();
    Session session = optionalSession.get();
    session.addParticipant(user);
    sessionRepository.save(session);
    return convertSessionToDto(session);
  }

  @Override
  public List<SessionsDTO> findAllSessionCurrentWeek() {
    return sessionRepository.findAllByWeek(LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR)).stream().map(session -> convertSessionToDto(session)).toList();
  }

  @Override
  public void removeParticipantFromSession(Long id, RemoveFromSessionRequest request) {
    Optional<Session> optionalSession = sessionRepository.findById(id);
    if (!optionalSession.isPresent()){
      throw new InvalidIdException();
    }
    Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
    if (!optionalUser.isPresent() || !optionalSession.get().getParticipants().contains(optionalUser.get())){
      throw new UserNotFoundException();
    }
    Session session = optionalSession.get();
    session.removeParticipantFromSession(optionalUser.get());
    sessionRepository.save(session);
  }

  @Scheduled(cron = "0 25 14 * * 5")
  private void createCurrentWeeksSessions(){
    for(int i = 3; i < 8; i++) {
      sessionRepository.save(new Session(LocalDate.now().plusDays(i)));
    }
  }

  private SessionsDTO convertSessionToDto(Session session){
    return new SessionsDTO(session.getDoDate(),session.getDay(),session.getWeek());
  }
}
