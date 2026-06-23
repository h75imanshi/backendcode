package com.eoffice.service;

import com.eoffice.model.Language;
import com.eoffice.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;

    public List<Language> getAllLanguages() {

        return repository.findByIsActive(1);

    }

}