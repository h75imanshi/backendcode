package com.eoffice.repository;

import com.eoffice.model.AnnualStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualStatementRepository extends JpaRepository<AnnualStatement, String> {

    List<AnnualStatement> findByRegNo(String regNo);

}