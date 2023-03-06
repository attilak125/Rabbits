package com.cala.rabbits.repositories.training;

import com.cala.rabbits.models.training.Wod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WodRepository extends JpaRepository<Wod, Long> {

}
