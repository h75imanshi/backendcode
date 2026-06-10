package com.eoffice.controller;

import com.eoffice.model.FilingDTO;
import com.eoffice.service.MandatoryFilingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mandatory-filings")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:4200}")
public class MandatoryFilingController {

    private final MandatoryFilingService service;

    @Value("${app.file.upload-dir:uploads/mandatory-filings}")
    private String uploadDir;

    // ── GET ALL ───────────────────────────────────────────
    @GetMapping
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    getAll() {
        List<FilingDTO.Response> list = service.findAll();
        return okList("Fetched all filings", list);
    }



    // ── GET BY ID  (complete detail) ──────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<FilingDTO.ApiResponse<FilingDTO.Response>>
    getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    FilingDTO.ApiResponse.ok("Record found",
                            service.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(FilingDTO.ApiResponse.error(e.getMessage()));
        }
    }

    // ── SEARCH — diary number ─────────────────────────────
    // GET /api/mandatory-filings/search/diary?diaryNumber=D1001
    @GetMapping("/search/diary")
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    byDiary(@RequestParam String diaryNumber) {
        return listResult(
                service.searchByDiaryNumber(diaryNumber.trim()),
                "No records for diary: " + diaryNumber);
    }

    // ── SEARCH — reg no ───────────────────────────────────
    // GET /api/mandatory-filings/search/regno?regNo=HARHIN/2014/61175
    @GetMapping("/search/regno")
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    byRegNo(@RequestParam String regNo) {
        return listResult(
                service.searchByRegNo(regNo.trim()),
                "No records for reg no: " + regNo);
    }

    // ── SEARCH — title ────────────────────────────────────
    // GET /api/mandatory-filings/search/title?titleName=NIRALA
    @GetMapping("/search/title")
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    byTitle(@RequestParam String titleName) {
        return listResult(
                service.searchByTitleName(titleName.trim()),
                "No records for title: " + titleName);
    }
    @GetMapping("/search")
    public ResponseEntity<
            FilingDTO.ApiResponse<
                    List<FilingDTO.Response>
                    >
            > universalSearch(
            @RequestParam String keyword
    ) {

        return listResult(
                service.universalSearch(
                        keyword.trim()
                ),
                "No records found"
        );

    }

    // ── SEARCH — file no ──────────────────────────────────
    // GET /api/mandatory-filings/search/fileno?fileNo=C-7-1
    @GetMapping("/search/fileno")
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    byFileNo(@RequestParam String fileNo) {
        return listResult(
                service.searchByFileNo(fileNo.trim()),   // see note below
                "No records for file no: " + fileNo);
    }

    // ── ADVANCED SEARCH ───────────────────────────────────
    @PostMapping("/search/advanced")
    public ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    advanced(@RequestBody FilingDTO.SearchRequest req) {
        log.info("Advanced search: {}", req);
        return listResult(service.advancedSearch(req), "No records found");
    }
    @GetMapping("/states")
    public ResponseEntity<List<String>> getStates() {

        return ResponseEntity.ok(
                service.getStates()
        );

    }

// ── DISTRICTS ──────────────────────────

    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistricts(
            @RequestParam String stateName) {

        return ResponseEntity.ok(
                service.getDistricts(stateName)
        );

    }


    // ── CREATE ────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<FilingDTO.ApiResponse<FilingDTO.Response>>
    create(@RequestBody FilingDTO.Request req) {
        try {
            return ResponseEntity.ok(FilingDTO.ApiResponse.ok(
                    "Filing created", service.create(req)));
        } catch (Exception e) {
            log.error("Create error", e);
            return ResponseEntity.badRequest()
                    .body(FilingDTO.ApiResponse.error(e.getMessage()));
        }
    }
// =========================================================
// INSERT FORM + DOCUMENT UPLOAD
// =========================================================

    @PostMapping("/insert")

    public ResponseEntity<?> insertWithDocuments(


            @RequestParam String diaryNumber,

            @RequestParam String regNo,

            @RequestParam String titleName,

            @RequestParam String periodicity,

            @RequestParam String language,

            @RequestParam String state,

            @RequestParam String district,

            @RequestParam String pinCode,

            @RequestParam String status,

            @RequestParam(required = false)
            String createdBy,

            @RequestParam(required = false)
            String ownerName,

            @RequestParam(required = false)
            String publisherName,


            @RequestParam(
                    value = "document1",
                    required = false
            )
            MultipartFile document1,

            @RequestParam(
                    value = "document2",
                    required = false
            )
            MultipartFile document2,
            @RequestParam(
                    value = "otherDocuments",
                    required = false
            )
            List<MultipartFile> otherDocuments

    ) {

        try {

            // =====================================================
            // REQUEST DTO
            // =====================================================

            FilingDTO.Request req =

                    FilingDTO.Request.builder()

                            .diaryNumber(diaryNumber)

                            .regNo(regNo)

                            .titleName(titleName)

                            .periodicity(periodicity)

                            .language(language)

                            .state(state)

                            .district(district)

                            .pinCode(pinCode)

                            .status(status)
                            .ownerName(ownerName)
                    .publisherName(publisherName)
                    .createdBy(createdBy)



                            .build();

            // =====================================================
            // SAVE DATABASE
            // =====================================================

            FilingDTO.Response saved =

                    service.create(req);

            // =====================================================
            // DOCUMENT 1
            // =====================================================

            if (document1 != null &&
                    !document1.isEmpty()) {

                service.uploadDocument(

                        saved.getId(),

                        document1,

                        uploadDir,

                        1

                );

            }

            // =====================================================
            // DOCUMENT 2
            // =====================================================

            if (document2 != null &&
                    !document2.isEmpty()) {

                service.uploadDocument(

                        saved.getId(),

                        document2,

                        uploadDir,

                        2

                );

            }
            if (otherDocuments != null) {

                int slot = 3;

                for (MultipartFile file : otherDocuments) {

                    if (!file.isEmpty()) {

                        service.uploadDocument(
                                saved.getId(),
                                file,
                                uploadDir,
                                slot++
                        );
                    }
                }
            }

            return ResponseEntity.ok(

                    FilingDTO.ApiResponse.ok(
                            "Record inserted successfully",
                            saved
                    )

            );

        }

        catch (Exception e) {

            log.error(
                    "Insert error",
                    e
            );

            return ResponseEntity.badRequest()
                    .body(

                            FilingDTO.ApiResponse.error(
                                    e.getMessage()
                            )

                    );
        }

    }
//
//    private Object ownerName(String ownerName) {
//        return null == ownerName ? "" : ownerName;
//
//    }


    // ── UPDATE ────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<FilingDTO.ApiResponse<FilingDTO.Response>>
    update(@PathVariable Long id,
           @RequestBody FilingDTO.Request req) {
        try {
            return ResponseEntity.ok(FilingDTO.ApiResponse.ok(
                    "Filing updated", service.update(id, req)));
        } catch (Exception e) {
            log.error("Update error", e);
            return ResponseEntity.badRequest()
                    .body(FilingDTO.ApiResponse.error(e.getMessage()));
        }
    }

    // =========================================================
    //  DOCUMENT 1 — Primary scanned PDF
    // =========================================================

    /** Upload primary PDF (stores file on server, saves path in DB) */
    @PostMapping("/{id}/document")
    public ResponseEntity<FilingDTO.ApiResponse<FilingDTO.Response>>
    uploadDoc1(@PathVariable Long id,
               @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(FilingDTO.ApiResponse.ok(
                    "Document 1 uploaded",
                    service.uploadDocument(id, file, uploadDir, 1)));
        } catch (Exception e) {
            log.error("Upload doc1 error", e);
            return ResponseEntity.badRequest()
                    .body(FilingDTO.ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Preview primary PDF inline in browser.
     * GET /api/mandatory-filings/{id}/document/preview
     */
    @GetMapping("/{id}/document/preview")
    public ResponseEntity<Resource> previewDoc1(@PathVariable Long id) {
        return servePdf(id, 1, true);
    }

    /**
     * Download primary PDF.
     * GET /api/mandatory-filings/{id}/document/download
     */
    @GetMapping("/{id}/document/download")
    public ResponseEntity<Resource> downloadDoc1(@PathVariable Long id) {
        return servePdf(id, 1, false);
    }

    // =========================================================
    //  DOCUMENT 2 — Secondary scanned PDF
    // =========================================================

    /** Upload secondary PDF */
    /**
     * Download secondary PDF.
     * GET /api/mandatory-filings/{id}/document2/download
     */
    @GetMapping("/{id}/document2/download")
    public ResponseEntity<Resource> downloadDoc2(@PathVariable Long id) {
        return servePdf(id, 2, false);
    }

// =========================================================
//  MULTIPLE PDF PREVIEW
// =========================================================

    @GetMapping("/{id}/documents/{fileName}/preview")
    public ResponseEntity<Resource> previewSpecificDocument(
            @PathVariable Long id,
            @PathVariable String fileName
    ) {

        try {

            FilingDTO.Response filing = service.findById(id);

            String basePath = filing.getFilePath();

            if (basePath == null || basePath.isBlank()) {
                return ResponseEntity.notFound().build();
            }

            java.nio.file.Path folderPath =
                    java.nio.file.Paths.get(basePath);

            // if stored path is file then get parent folder
            if (java.nio.file.Files.isRegularFile(folderPath)) {
                folderPath = folderPath.getParent();
            }

            java.nio.file.Path filePath =
                    folderPath.resolve(fileName);

            if (!java.nio.file.Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource =
                    new org.springframework.core.io.UrlResource(
                            filePath.toUri()
                    );

            String contentType =
                    java.nio.file.Files.probeContentType(filePath);

            if (contentType == null) {
                contentType = "application/pdf";
            }

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + fileName + "\""
                    )
                    .contentType(
                            MediaType.parseMediaType(contentType)
                    )
                    .body(resource);

        } catch (Exception ex) {

            ex.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }

// =========================================================
//  Private helpers
// =========================================================

    private ResponseEntity<Resource> servePdf(Long id,
                                              int slot,
                                              boolean inline) {
        try {

            Resource resource = service.loadDocument(id, slot);

            String filename = resource.getFilename();

            String ct = resolveContentType(filename);

            String disp =
                    (inline ? "inline" : "attachment")
                            + "; filename=\""
                            + (filename != null
                            ? filename
                            : "document.pdf")
                            + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(ct))
                    .header(HttpHeaders.CONTENT_DISPOSITION, disp)
                    .header("X-Frame-Options", "SAMEORIGIN")
                    .body(resource);

        } catch (Exception e) {

            log.error(
                    "Error serving PDF slot={} id={}",
                    slot,
                    id,
                    e
            );

            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    listResult(List<FilingDTO.Response> results, String emptyMsg) {
        if (results == null || results.isEmpty()) {
            FilingDTO.ApiResponse<List<FilingDTO.Response>> r =
                    FilingDTO.ApiResponse.error(emptyMsg);
            r.setData(List.of()); r.setCount(0);
            return ResponseEntity.ok(r);
        }
        return okList("Records found", results);
    }

    private ResponseEntity<FilingDTO.ApiResponse<List<FilingDTO.Response>>>
    okList(String msg, List<FilingDTO.Response> data) {
        FilingDTO.ApiResponse<List<FilingDTO.Response>> r =
                FilingDTO.ApiResponse.ok(msg, data);
        r.setCount(data.size());
        return ResponseEntity.ok(r);
    }


    private String resolveContentType(String filename) {
        if (filename == null) return "application/octet-stream";
        String f = filename.toLowerCase();
        if (f.endsWith(".pdf"))               return "application/pdf";
        if (f.endsWith(".jpg")||f.endsWith(".jpeg")) return "image/jpeg";
        if (f.endsWith(".png"))               return "image/png";
        if (f.endsWith(".tiff")||f.endsWith(".tif")) return "image/tiff";
        if (f.endsWith(".docx"))              return
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        return "application/octet-stream";
    }
}