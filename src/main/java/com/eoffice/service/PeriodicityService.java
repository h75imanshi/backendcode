package com.eoffice.service;

import com.eoffice.model.Periodicity;
import com.eoffice.repository.PeriodicityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodicityService {

    private final PeriodicityRepository repository;

    public List<Periodicity> getAllPeriodicity() {
        return repository.findByIsActive(1);
    }
}