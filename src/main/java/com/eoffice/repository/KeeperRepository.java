package com.eoffice.repository;

import com.eoffice.model.KeeperDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeeperRepository
        extends JpaRepository<KeeperDetails, String> {

    List<KeeperDetails> findByPressId(String pressId);

}