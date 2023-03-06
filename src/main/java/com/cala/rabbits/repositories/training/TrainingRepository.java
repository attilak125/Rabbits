package com.cala.rabbits.repositories.training;

import com.cala.rabbits.models.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {

//  List<Training> findAllByType(String type);
//  Optional<Training> findByDoDate(LocalDate doDate);
//  List<Training> findAllByWeek(int week);
}
