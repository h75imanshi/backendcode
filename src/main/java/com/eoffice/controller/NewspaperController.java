package com.eoffice.controller;

import com.eoffice.model.NewspaperDetails;
import com.eoffice.service.NewspaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newspaper")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewspaperController {

    private final NewspaperService service;

    @PostMapping("/add")
    public NewspaperDetails save(
            @RequestBody NewspaperDetails details) {

        return service.save(details);
    }

    @GetMapping("/{regNo}")
    public NewspaperDetails get(
            @PathVariable String regNo) {

        return service.getByRegNo(regNo);
    }
}