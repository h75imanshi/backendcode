package com.eoffice.controller;

import com.eoffice.model.KeeperDetails;
import com.eoffice.repository.KeeperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keeper")
@RequiredArgsConstructor
@CrossOrigin("*")
public class KeeperController {

    private final KeeperRepository keeperRepository;

    @GetMapping("/press/{pressId}")
    public List<KeeperDetails> getKeeperByPressId(
            @PathVariable String pressId) {

        return keeperRepository.findByPressId(pressId);
    }
}