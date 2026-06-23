package com.eoffice.service;

import com.eoffice.model.LocationDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class MapService {

    private final RestTemplate restTemplate;

    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LocationDto geocode(String address) {

        try {

            System.out.println("================================");
            System.out.println("ORIGINAL ADDRESS = " + address);

            if (address == null || address.trim().isEmpty()) {
                return null;
            }

            // Address Cleaning
            String cleanAddress = address;

            cleanAddress = cleanAddress.replaceAll("\\d{6}", "");
            cleanAddress = cleanAddress.replace("DISTT.-", "");
            cleanAddress = cleanAddress.replace("DISTT.", "");
            cleanAddress = cleanAddress.replace("-", " ");
            cleanAddress = cleanAddress.replaceAll("\\s+", " ").trim();

            System.out.println("CLEAN ADDRESS = " + cleanAddress);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("User-Agent", "EOffice/1.0");

            HttpEntity<String> entity =
                    new HttpEntity<>(headers);

            String url =
                    "https://nominatim.openstreetmap.org/search?q="
                            + URLEncoder.encode(
                            cleanAddress + ", India",
                            StandardCharsets.UTF_8
                    )
                            + "&format=json&limit=5";

            System.out.println("URL = " + url);

            ResponseEntity<List> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            List.class
                    );

            List<Map<String, Object>> result =
                    response.getBody();

            System.out.println("RESULT = " + result);

            // ======================
            // FALLBACK SEARCH
            // ======================

            if (result == null || result.isEmpty()) {

                System.out.println("TRYING FALLBACK SEARCH");

                String fallback = cleanAddress;

                String[] parts = cleanAddress.split(",");

                if (parts.length >= 2) {

                    fallback =
                            parts[parts.length - 2].trim()
                                    + ", "
                                    + parts[parts.length - 1].trim();

                } else {

                    String[] words =
                            cleanAddress.split(" ");

                    if (words.length >= 2) {

                        fallback =
                                words[words.length - 2]
                                        + " "
                                        + words[words.length - 1];
                    }
                }

                System.out.println("FALLBACK = " + fallback);

                String fallbackUrl =
                        "https://nominatim.openstreetmap.org/search?q="
                                + URLEncoder.encode(
                                fallback + ", India",
                                StandardCharsets.UTF_8
                        )
                                + "&format=json&limit=1";

                System.out.println("FALLBACK URL = " + fallbackUrl);

                ResponseEntity<List> fallbackResponse =
                        restTemplate.exchange(
                                fallbackUrl,
                                HttpMethod.GET,
                                entity,
                                List.class
                        );

                result = fallbackResponse.getBody();

                System.out.println(
                        "FALLBACK RESULT = " + result
                );

                if (result == null || result.isEmpty()) {

                    System.out.println(
                            "NO LOCATION FOUND EVEN AFTER FALLBACK"
                    );

                    return null;
                }
            }

            Map<String, Object> location =
                    result.get(0);

            System.out.println(
                    "LOCATION FOUND = " + location
            );

            LocationDto dto =
                    new LocationDto();

            dto.setLat(
                    Double.parseDouble(
                            location.get("lat").toString()
                    )
            );

            dto.setLon(
                    Double.parseDouble(
                            location.get("lon").toString()
                    )
            );

            return dto;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}