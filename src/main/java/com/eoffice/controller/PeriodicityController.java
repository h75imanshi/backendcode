package com.eoffice.controller;

import com.eoffice.model.Periodicity;
import com.eoffice.service.PeriodicityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodicity")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PeriodicityController {

    private final PeriodicityService service;

    @GetMapping
    public List<Periodicity> getAll() {
        return service.getAllPeriodicity();
    }
}