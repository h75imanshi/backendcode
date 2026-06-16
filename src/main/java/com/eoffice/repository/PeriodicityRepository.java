package com.eoffice.repository;

import com.eoffice.model.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicityRepository extends JpaRepository<Periodicity, Integer> {

    List<Periodicity> findByIsActive(Integer isActive);

}