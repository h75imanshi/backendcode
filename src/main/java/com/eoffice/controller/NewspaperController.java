package com.eoffice.controller;


import com.eoffice.dto.NewspaperDto;

import com.eoffice.service.NewspaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/newspaper")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NewspaperController {

    private final NewspaperService newspaperService;

    @GetMapping("/press-name/{pressName}")
    public ResponseEntity<?> getByPressName(
            @PathVariable String pressName) {

        return ResponseEntity.ok(
                newspaperService.getByPressName(pressName));

    }
}