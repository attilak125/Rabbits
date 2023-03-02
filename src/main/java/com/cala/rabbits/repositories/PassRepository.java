package com.cala.rabbits.repositories;

import com.cala.rabbits.models.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepository extends JpaRepository<Pass,Long> {

}
