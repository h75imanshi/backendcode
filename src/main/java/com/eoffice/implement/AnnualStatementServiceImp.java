package com.eoffice.implement;

import com.eoffice.model.AnnualStatement;
import com.eoffice.repository.AnnualStatementRepository;
import com.eoffice.service.AnnualStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualStatementServiceImp implements AnnualStatementService {

    @Autowired
    private AnnualStatementRepository annualStatementRepository;

    @Override
    public List<AnnualStatement> getAnnualStatementByRegNo(String regNo) {

        return annualStatementRepository.findByRegNo(regNo);

    }
}