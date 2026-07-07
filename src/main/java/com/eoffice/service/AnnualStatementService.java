package com.eoffice.service;

import com.eoffice.model.AnnualStatement;

import java.util.List;

public interface AnnualStatementService {

    List<AnnualStatement> getAnnualStatementByRegNo(String regNo);

}