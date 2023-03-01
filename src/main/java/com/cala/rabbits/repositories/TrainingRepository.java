package com.cala.rabbits.repositories;

import com.cala.rabbits.models.Training;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {

  List<Training> findAllByType(String type);
}
