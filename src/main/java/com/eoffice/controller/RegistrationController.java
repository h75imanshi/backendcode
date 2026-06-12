package com.eoffice.controller;

import com.eoffice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegistrationService service;

    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> search(
            @RequestParam String value) {

        return ResponseEntity.ok(
                service.search(value));

    }
}