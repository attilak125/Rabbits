package com.cala.rabbits.repositories.user;

import com.cala.rabbits.models.user.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepository extends JpaRepository<Pass,Long> {

}
