package com.eoffice.controller;

import com.eoffice.model.AnnualStatement;
import com.eoffice.service.AnnualStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annual-statement")
@CrossOrigin(origins = "*")
public class AnnualStatementController {

    @Autowired
    private AnnualStatementService annualStatementService;

    @GetMapping("/{regNo}")
    public List<AnnualStatement> getAnnualStatement(@PathVariable String regNo) {

        return annualStatementService.getAnnualStatementByRegNo(regNo);

    }
}