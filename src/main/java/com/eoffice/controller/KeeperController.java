package com.eoffice.controller;

import com.eoffice.dto.KeeperDto;
import com.eoffice.model.KeeperDetails;
import com.eoffice.repository.KeeperRepository;
import com.eoffice.service.KeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keeper")
@RequiredArgsConstructor
public class KeeperController {

    private final KeeperRepository keeperRepository;

    @GetMapping("/press/{pressId}")
    public Object getByPressId(
            @PathVariable String pressId) {

        return keeperRepository.getKeepersByPressId(pressId);
    }
}