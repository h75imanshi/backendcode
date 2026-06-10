package com.eoffice.service;

import com.eoffice.model.NewspaperDetails;
import com.eoffice.repository.NewspaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewspaperService {

    private final NewspaperRepository repository;

    public NewspaperDetails save(NewspaperDetails details) {
        return repository.save(details);
    }

    public NewspaperDetails getByRegNo(String regNo) {
        return repository.findByRegNo(regNo);
    }
}