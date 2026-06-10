package com.eoffice.repository;

import com.eoffice.model.NewspaperDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewspaperRepository
        extends JpaRepository<NewspaperDetails, Long> {

    NewspaperDetails findByRegNo(String regNo);
}