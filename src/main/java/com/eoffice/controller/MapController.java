package com.eoffice.controller;
import com.eoffice.model.LocationDto;
import com.eoffice.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MapController {

    private final MapService mapService;

    @GetMapping("/geocode")
    public LocationDto geocode(
            @RequestParam String address
    ) {

        System.out.println(
                "ADDRESS = " + address
        );

        return mapService.geocode(address);
    }
}