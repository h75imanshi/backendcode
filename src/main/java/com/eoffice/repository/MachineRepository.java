package com.eoffice.repository;

import com.eoffice.model.MachineDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineRepository
        extends JpaRepository<MachineDetails, String> {

    List<MachineDetails> findByPressId(String pressId);

}