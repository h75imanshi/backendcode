package com.eoffice.service;

import com.eoffice.model.FilingDTO;
import com.eoffice.model.MandatoryFiling;
import com.eoffice.repository.MandatoryFilingRepository;
import com.eoffice.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
public class MandatoryFilingService {

    // ── REPOSITORIES ─────────────────────────────────────
    private final MandatoryFilingRepository repo;
    private final StateRepository stateRepository;


    // ── ENTITY TO RESPONSE DTO ────────────────────────────
    private FilingDTO.Response toResponse(MandatoryFiling e) {
        List<String> docs = findAllDocuments(e);

        return FilingDTO.Response.builder()
                .id(e.getId())
                .diaryNumber(e.getDiaryNumber())
                .titleName(e.getTitleName())
                .regNo(e.getRegNo())
                .fileNo(e.getFileNo())
                .sectionName(e.getSectionName())
                .state(e.getState())
                .district(e.getDistrict())
                .language(e.getLanguage())
                .pinCode(e.getPinCodes())
                .ownerName(e.getOwnerName())
                .publisherName(e.getPublisherName())
                .periodicity(e.getPeriodicity() != null
                        ? e.getPeriodicity().name() : null)
                .status(e.getStatus() != null
                        ? e.getStatus().name() : null)
                .createdBy(e.getCreatedBy())
                .documentName(e.getDocumentName())
                .documentName2(e.getDocumentName2())
                .filePath(e.getFilePath())
                .filePath2(e.getFilePath2())
                .documents(docs)
                .hasDocument(!docs.isEmpty())
                .hasDocument2(notBlank(e.getFilePath2()))
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())

                // OWNER
                .ownerAddress(e.getOwnerAddress())
                .ownerState(e.getOwnerState())
                .ownerDistrict(e.getOwnerDistrict())
                .ownerCity(e.getOwnerCity())
                .ownerPincode(e.getOwnerPincode())

                // PUBLISHER
                .publisherAddress(e.getPublisherAddress())
                .publisherState(e.getPublisherState())
                .publisherDistrict(e.getPublisherDistrict())
                .publisherCity(e.getPublisherCity())
                .publisherPincode(e.getPublisherPincode())

                // EDITOR
                .editorName(e.getEditorName())
                .editorAddress(e.getEditorAddress())
                .editorState(e.getEditorState())
                .editorDistrict(e.getEditorDistrict())
                .editorCity(e.getEditorCity())
                .editorPincode(e.getEditorPincode())

                // PUBLICATION
                .publicationAddress(e.getPublicationAddress())
                .publicationState(e.getPublicationState())
                .publicationDistrict(e.getPublicationDistrict())
                .publicationCity(e.getPublicationCity())
                .publicationPincode(e.getPublicationPincode())

                // PRINTING PRESS
                .printerName(e.getPrinterName())
                .pressName(e.getPressName())
                .pressAddress(e.getPressAddress())
                .pressState(e.getPressState())
                .pressDistrict(e.getPressDistrict())
                .pressCity(e.getPressCity())
                .pressPincode(e.getPressPincode())

                // REVISION
//                .revisionTitleName(e.getRevisionTitleName())
//                .revisionRegNo(e.getRevisionRegNo())
//                .revisionReason(e.getRevisionReason())
//                .revisionChanges(e.getRevisionChanges())
//                .revisionDate(e.getRevisionDate())

                .build();
    }


    // ── FIND ALL ──────────────────────────────────────────
    public List<FilingDTO.Response> findAll() {
        return repo.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    // ── FIND BY ID ────────────────────────────────────────
    public FilingDTO.Response findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    // ── SEARCH — DIARY NUMBER ─────────────────────────────
    public List<FilingDTO.Response> searchByDiaryNumber(String v) {
        return repo.findByDiaryNumberContainingIgnoreCase(v)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── SEARCH — REG NO ───────────────────────────────────
    public List<FilingDTO.Response> searchByRegNo(String v) {
        return repo.findByRegNoContainingIgnoreCase(v)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── SEARCH — TITLE ────────────────────────────────────
    public List<FilingDTO.Response> searchByTitleName(String v) {
        return repo.findByTitleNameContainingIgnoreCase(v)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── SEARCH — UNIVERSAL ────────────────────────────────
    public List<FilingDTO.Response> universalSearch(String keyword) {
        return repo
                .universalSearch(keyword)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── SEARCH — ADVANCED ────────────────────────────────
    public List<FilingDTO.Response> advancedSearch(FilingDTO.SearchRequest r) {
        return repo.advancedSearch(
                blank(r.getDiaryNumber()), blank(r.getRegNo()),
                blank(r.getTitleName()),   blank(r.getFileNo()),
                blank(r.getSectionName()), blank(r.getState()),
                blank(r.getDistrict()),    blank(r.getOwnerName()),
                blank(r.getPublisherName()), blank(r.getPeriodicity()),
                blank(r.getStatus())
        ).stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── CREATE ────────────────────────────────────────────
    public FilingDTO.Response create(FilingDTO.Request req) {

        MandatoryFiling e = MandatoryFiling.builder()
                .diaryNumber(req.getDiaryNumber())
                .titleName(req.getTitleName())
                .regNo(req.getRegNo())
                .fileNo(req.getFileNo())
                .sectionName(req.getSectionName())
                .state(req.getState() != null ? req.getState() : "")
                .district(req.getDistrict())
                .language(req.getLanguage())
                .pinCode(req.getPinCode())
                .ownerName(req.getOwnerName())
                .publisherName(req.getPublisherName())
                .createdBy(req.getCreatedBy())
                .periodicity(parsePeriodicity(req.getPeriodicity()))
                .status(req.getStatus() != null
                        ? MandatoryFiling.Status.valueOf(req.getStatus())
                        : MandatoryFiling.Status.PENDING)

//                // DAK SECTION
//                .dakReceivedDate(req.getDakReceivedDate())
//                .dakDiaryNo(req.getDakDiaryNo())
//                .dakState(req.getDakState())
//                .dakDistrict(req.getDakDistrict())
//                .dakSection(req.getDakSection())
//                .dakProcessed(req.getDakProcessed())
//                .dakForwardTo(req.getDakForwardTo())

                .build();

        System.out.println("DTO CREATED BY = " + req.getCreatedBy());
        System.out.println("ENTITY CREATED BY = " + e.getCreatedBy());

        MandatoryFiling saved = repo.save(e);

        System.out.println("SAVED CREATED BY = " + saved.getCreatedBy());

        return toResponse(saved);
    }

    // ── UPDATE ────────────────────────────────────────────
    public FilingDTO.Response update(Long id, FilingDTO.Request req) {
        MandatoryFiling e = getOrThrow(id);
        e.setDiaryNumber(req.getDiaryNumber());
        e.setTitleName(req.getTitleName());
        e.setRegNo(req.getRegNo());
        e.setFileNo(req.getFileNo());
        e.setSectionName(req.getSectionName());
        e.setState(req.getState());
        e.setDistrict(req.getDistrict());
        e.setLanguage(req.getLanguage());
        e.setPinCode(req.getPinCode());
        e.setOwnerName(req.getOwnerName());
        e.setPublisherName(req.getPublisherName());
        e.setPeriodicity(parsePeriodicity(req.getPeriodicity()));
        if (req.getStatus() != null) {
            try {
                e.setStatus(MandatoryFiling.Status.valueOf(req.getStatus()));
            } catch (IllegalArgumentException ignored) {}
        }
        return toResponse(repo.save(e));
    }

    // ── PDF UPLOAD ────────────────────────────────────────
    public FilingDTO.Response uploadDocument(Long id,
                                             MultipartFile file,
                                             String uploadDir,
                                             int slot) throws IOException {
        MandatoryFiling e = getOrThrow(id);
        Path dir = Paths.get(uploadDir);
        if (!Files.exists(dir)) Files.createDirectories(dir);

        String clean = StringUtils.cleanPath(
                file.getOriginalFilename() != null
                        ? file.getOriginalFilename() : "document.pdf");
        String stored = id + "_slot" + slot + "_" + clean;
        Path dest = dir.resolve(stored);
        Files.copy(file.getInputStream(), dest,
                StandardCopyOption.REPLACE_EXISTING);

        switch (slot) {
            case 1:
                e.setFilePath(dest.toAbsolutePath().toString());
                e.setDocumentName(clean);
                e.setDocumentType(detectType(clean));
                break;
            case 2:
                e.setFilePath2(dest.toAbsolutePath().toString());
                e.setDocumentName2(clean);
                e.setDocumentType2(detectType(clean));
                break;
            case 3:  e.setPdf1(dest.toAbsolutePath().toString());  break;
            case 4:  e.setPdf2(dest.toAbsolutePath().toString());  break;
            case 5:  e.setPdf3(dest.toAbsolutePath().toString());  break;
            case 6:  e.setPdf4(dest.toAbsolutePath().toString());  break;
            case 7:  e.setPdf5(dest.toAbsolutePath().toString());  break;
            case 8:  e.setPdf6(dest.toAbsolutePath().toString());  break;
            case 9:  e.setPdf7(dest.toAbsolutePath().toString());  break;
            case 10: e.setPdf8(dest.toAbsolutePath().toString());  break;
            case 11: e.setPdf9(dest.toAbsolutePath().toString());  break;
            case 12: e.setPdf10(dest.toAbsolutePath().toString()); break;
        }
        repo.save(e);
        return toResponse(e);
    }

    // ── PDF SERVE ─────────────────────────────────────────
    public Resource loadDocument(Long id, int slot) throws IOException {
        MandatoryFiling e = getOrThrow(id);
        String rawPath = switch (slot) {
            case 1  -> e.getFilePath();
            case 2  -> e.getFilePath2();
            case 3  -> e.getPdf1();
            case 4  -> e.getPdf2();
            case 5  -> e.getPdf3();
            case 6  -> e.getPdf4();
            case 7  -> e.getPdf5();
            case 8  -> e.getPdf6();
            case 9  -> e.getPdf7();
            case 10 -> e.getPdf8();
            case 11 -> e.getPdf9();
            case 12 -> e.getPdf10();
            default -> null;
        };

        if (!notBlank(rawPath))
            throw new RuntimeException(
                    "No document (slot " + slot + ") for filing id: " + id);

        Path filePath = Paths.get(rawPath);

        if (Files.isDirectory(filePath)) {
            filePath = Files.list(filePath)
                    .filter(p -> isServable(p.getFileName().toString()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "No servable file found in folder: " + rawPath));
        }

        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable())
            throw new RuntimeException("File not readable: " + filePath);
        return resource;
    }

    // ── FIND ALL DOCUMENTS ────────────────────────────────
    private List<String> findAllDocuments(MandatoryFiling e) {
        List<String> docs = new ArrayList<>();
        addDocumentsFromPath(docs, e.getFilePath());
        addDocumentsFromPath(docs, e.getFilePath2());
        addDocumentsFromPath(docs, e.getPdf1());
        addDocumentsFromPath(docs, e.getPdf2());
        addDocumentsFromPath(docs, e.getPdf3());
        addDocumentsFromPath(docs, e.getPdf4());
        addDocumentsFromPath(docs, e.getPdf5());
        addDocumentsFromPath(docs, e.getPdf6());
        addDocumentsFromPath(docs, e.getPdf7());
        addDocumentsFromPath(docs, e.getPdf8());
        addDocumentsFromPath(docs, e.getPdf9());
        addDocumentsFromPath(docs, e.getPdf10());
        return docs.stream().distinct().collect(Collectors.toList());
    }

    private void addDocumentsFromPath(List<String> docs, String rawPath) {
        if (!notBlank(rawPath)) return;
        try {
            Path path = Paths.get(rawPath);
            if (Files.isDirectory(path)) {
                try (var stream = Files.list(path)) {
                    stream
                            .filter(Files::isRegularFile)
                            .filter(p -> isServable(p.getFileName().toString()))
                            .map(p -> p.getFileName().toString())
                            .sorted()
                            .forEach(docs::add);
                }
            } else if (Files.exists(path) && isServable(path.getFileName().toString())) {
                docs.add(path.getFileName().toString());
            }
        } catch (Exception ex) {
            log.warn("Unable to read document path: {}", rawPath, ex);
        }
    }



    // ── STATES & DISTRICTS ────────────────────────────────
    public List<String> getStates() {
        return stateRepository.getStates();
    }

    public List<String> getDistricts(String stateName) {
        return stateRepository.getDistrictsByState(stateName);
    }

    public List<FilingDTO.Response> searchByFileNo(String trim) {
        return null;
    }

    // ── PRIVATE HELPERS ───────────────────────────────────
    private MandatoryFiling getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new RuntimeException("Filing not found with id: " + id));
    }

    private boolean notBlank(String s) {
        return s != null && !s.isBlank();
    }

    private String blank(String s) {
        return (s == null || s.isBlank()) ? null : s.trim();
    }

    private MandatoryFiling.Periodicity parsePeriodicity(String v) {
        if (v == null || v.isBlank()) return null;
        try {
            return MandatoryFiling.Periodicity.valueOf(v.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String detectType(String filename) {
        if (filename == null) return null;
        String f = filename.toLowerCase();
        if (f.endsWith(".pdf"))  return "application/pdf";
        if (f.endsWith(".jpg") || f.endsWith(".jpeg")) return "image/jpeg";
        if (f.endsWith(".png"))  return "image/png";
        if (f.endsWith(".tiff") || f.endsWith(".tif")) return "image/tiff";
        return "application/octet-stream";
    }

    private boolean isServable(String name) {
        String n = name.toLowerCase();
        return n.endsWith(".pdf") || n.endsWith(".jpg")
                || n.endsWith(".jpeg") || n.endsWith(".png")
                || n.endsWith(".tiff") || n.endsWith(".tif");
    }
}