package com.eoffice.controller;

import com.eoffice.model.Language;
import com.eoffice.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LanguageController {

    private final LanguageService service;

    @GetMapping
    public List<Language> getAllLanguages() {

        return service.getAllLanguages();

    }
}