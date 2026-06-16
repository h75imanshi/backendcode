package com.eoffice.controller;

import com.eoffice.dto.RegistrationProjection;
import com.eoffice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegistrationService service;

    @GetMapping("/search")
    public List<RegistrationProjection> search(
            @RequestParam String value) {

        return service.search(value);
    }
}