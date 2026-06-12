package com.eoffice.service;

import com.eoffice.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository repository;

    public List<Object[]> search(String value) {

        List<Object[]> result =
                repository.searchRegistration(value);

        System.out.println("SEARCH = " + value);
        System.out.println("TOTAL RECORDS = " + result.size());

        return result;
    }
}