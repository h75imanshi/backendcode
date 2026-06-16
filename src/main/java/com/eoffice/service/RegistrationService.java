package com.eoffice.service;

import com.eoffice.dto.RegistrationProjection;
import com.eoffice.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository repository;

    public List<RegistrationProjection> search(String value) {
        return repository.searchCompleteData(value);
    }

}