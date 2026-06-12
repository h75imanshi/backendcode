package com.eoffice.controller;

import com.eoffice.dto.PressDetailDto;
import com.eoffice.dto.PressSearchResultDto;
import com.eoffice.service.PressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/press")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PressController {


    private final PressService pressService;

    // Search by Press Name
    @GetMapping("/search/name")
    public ResponseEntity<?> searchByName(
            @RequestParam(defaultValue = "") String name) {

        try {

            if (name == null || name.trim().length() < 3) {

                return ResponseEntity.badRequest()
                        .body("Enter minimum 3 characters for Press Name");
            }

            List<PressSearchResultDto> result =
                    pressService.searchByName(name);

            System.out.println("==========");
            System.out.println("Search Name = " + name);
            System.out.println("Records Found = " + result.size());

            for (PressSearchResultDto p : result) {

                System.out.println(
                        "PRESS = " + p.getPressName()
                                + " | STATE = " + p.getState()
                                + " | DISTRICT = " + p.getDistrict()
                );
            }

            System.out.println("==========");

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body("ERROR : "
                            + e.getClass().getName()
                            + " -> "
                            + e.getMessage());
        }
    }
    //Full Press Detail
    @GetMapping("/{id}/detail")
    public ResponseEntity<PressDetailDto> getFullDetail(
            @PathVariable String id) {

        return ResponseEntity.ok(
                pressService.getFullDetail(String.valueOf(id))
        );
    }



    // Search by Application Number
    @GetMapping("/search/appno")
    public ResponseEntity<List<PressSearchResultDto>> searchByAppNo(
            @RequestParam String appNo) {

        if (appNo == null || appNo.trim().length() < 3) {
            throw new RuntimeException(
                    "Enter minimum 3 characters for Application No"
            );
        }

        return ResponseEntity.ok(
                pressService.searchByAppNo(appNo)
        );
    }

    @GetMapping("/search/printer")
    public ResponseEntity<List<PressSearchResultDto>> searchByPrinter(
            @RequestParam String name) {

        if (name == null || name.trim().length() < 3) {
            throw new RuntimeException(
                    "Enter minimum 3 characters for Printer Name"
            );
        }

        return ResponseEntity.ok(
                pressService.searchByPrinterName(name)
        );
    }



    // Get All States
    @GetMapping("/states")
    public ResponseEntity<List<String>> getStates() {

        return ResponseEntity.ok(
                pressService.getStates()
        );
    }

    // Get Districts By State
    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistricts(
            @RequestParam String stateName) {

        return ResponseEntity.ok(
                pressService.getDistricts(stateName)
        );
    }

    // REMOVE OR COMMENT

    @GetMapping("/search/state")
    public ResponseEntity<List<PressSearchResultDto>> searchByState(
            @RequestParam String state,
            @RequestParam(required = false) String district) {

        return ResponseEntity.ok(
                pressService.searchByState(state, district)
        );
    }


}
