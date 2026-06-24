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

            // ============================================
            // FIX: Address cleaning - brackets ko handle karo
            // ============================================
            String cleanAddress = address;

            // FIX: "BHOPAL (MADHYA PRADESH)" jaisa format Nominatim ko
            // confuse karta hai. Bracket ke andar ka text (state name)
            // nikalo aur use comma se alag karo:
            // "BHOPAL (MADHYA PRADESH)" -> "BHOPAL, MADHYA PRADESH"
            cleanAddress = cleanAddress.replaceAll("\\(([^)]*)\\)", ", $1");

            // PIN code remove
            cleanAddress = cleanAddress.replaceAll("\\d{6}", "");

            // DISTT. / DISTT removal
            cleanAddress = cleanAddress.replace("DISTT.-", "");
            cleanAddress = cleanAddress.replace("DISTT.", "");
            cleanAddress = cleanAddress.replace("DISTT", "");

            // FIX: "H.NO." jaisa abbreviation Nominatim ke liye noise hai,
            // building number ka exact match shaayad hi milta hai, but
            // ise hata dene se baaki address (locality, city, state)
            // match hone ke chances badhte hain
            cleanAddress = cleanAddress.replaceAll("(?i)H\\.?NO\\.?", "");

            cleanAddress = cleanAddress.replace("-", " ");

            // Double commas, leading/trailing commas/spaces clean karo
            cleanAddress = cleanAddress.replaceAll(",\\s*,", ",");
            cleanAddress = cleanAddress.replaceAll("^\\s*,\\s*", "");
            cleanAddress = cleanAddress.replaceAll("\\s*,\\s*$", "");
            cleanAddress = cleanAddress.replaceAll("\\s+", " ").trim();

            System.out.println("CLEAN ADDRESS = " + cleanAddress);

            LocationDto found = tryGeocode(cleanAddress + ", India");

            if (found != null) {
                return found;
            }

            // ============================================
            // FALLBACK 1: last 2 comma-separated parts (city, state)
            // ============================================
            System.out.println("TRYING FALLBACK 1 (city, state)");

            String fallback1 = buildFallback(cleanAddress, 2);
            System.out.println("FALLBACK 1 = " + fallback1);

            if (fallback1 != null) {
                found = tryGeocode(fallback1 + ", India");
                if (found != null) {
                    return found;
                }
            }

            // ============================================
            // FALLBACK 2: sirf last part (state ya city)
            // ============================================
            System.out.println("TRYING FALLBACK 2 (state/city only)");

            String fallback2 = buildFallback(cleanAddress, 1);
            System.out.println("FALLBACK 2 = " + fallback2);

            if (fallback2 != null) {
                found = tryGeocode(fallback2 + ", India");
                if (found != null) {
                    return found;
                }
            }

            System.out.println("NO LOCATION FOUND EVEN AFTER ALL FALLBACKS");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Comma-separated address ke last N parts jodta hai.
     * e.g. buildFallback("A, B, C, D", 2) -> "C, D"
     */
    private String buildFallback(String cleanAddress, int lastNParts) {

        String[] parts = cleanAddress.split(",");

        // Empty parts hatao (trailing/leading commas se khali strings ban sakti hain)
        java.util.List<String> nonEmptyParts = new java.util.ArrayList<>();
        for (String p : parts) {
            if (!p.trim().isEmpty()) {
                nonEmptyParts.add(p.trim());
            }
        }

        if (nonEmptyParts.size() < lastNParts) {
            if (nonEmptyParts.isEmpty()) {
                return null;
            }
            // Available jitne parts hain woh hi use karo
            lastNParts = nonEmptyParts.size();
        }

        StringBuilder sb = new StringBuilder();
        int startIdx = nonEmptyParts.size() - lastNParts;

        for (int i = startIdx; i < nonEmptyParts.size(); i++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(nonEmptyParts.get(i));
        }

        return sb.toString();
    }

    /**
     * Ek Nominatim call karta hai aur pehla result LocationDto me convert karta hai.
     * Agar kuch na mile to null return karta hai (exception nahi throw karta).
     */
    @SuppressWarnings("unchecked")
    private LocationDto tryGeocode(String queryAddress) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("User-Agent", "EOffice/1.0");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            String url =
                    "https://nominatim.openstreetmap.org/search?q="
                            + URLEncoder.encode(queryAddress, StandardCharsets.UTF_8)
                            + "&format=json&limit=5&countrycodes=in";

            System.out.println("URL = " + url);

            ResponseEntity<List> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, List.class
            );

            List<Map<String, Object>> result = response.getBody();

            System.out.println("RESULT = " + result);

            if (result == null || result.isEmpty()) {
                return null;
            }

            Map<String, Object> location = result.get(0);

            System.out.println("LOCATION FOUND = " + location);

            LocationDto dto = new LocationDto();
            dto.setLat(Double.parseDouble(location.get("lat").toString()));
            dto.setLon(Double.parseDouble(location.get("lon").toString()));

            return dto;

        } catch (Exception e) {
            System.out.println("tryGeocode error for [" + queryAddress + "]: " + e.getMessage());
            return null;
        }
    }
}