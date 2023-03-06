package com.cala.rabbits.repositories.training;

import com.cala.rabbits.models.training.Session;
import com.cala.rabbits.models.training.dto.SessionsDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

  List<Session> findAllByWeek(int week);
}
