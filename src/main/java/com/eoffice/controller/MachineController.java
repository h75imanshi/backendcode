package com.eoffice.controller;

import com.eoffice.model.MachineDetails;
import com.eoffice.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machine")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MachineController {

    private final MachineRepository machineRepository;

    @GetMapping("/press/{pressId}")
    public List<MachineDetails> getMachineByPressId(
            @PathVariable String pressId) {

        return machineRepository.findByPressId(pressId);
    }
}