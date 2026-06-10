package com.eoffice.model;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

public class FilingDTO {

    // ── CREATE / UPDATE REQUEST ───────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Request {
        private String diaryNumber;
        private String titleName;
        private String regNo;
        private String fileNo;
        private String sectionName;
        private String state;
        private String district;
        private String ownerName;
        private String publisherName;
        private String periodicity;
        private String status;
        private String createdBy;
        private String language;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
        private String pinCode;

        // OWNER
        private String ownerAddress;
        private String ownerState;
        private String ownerDistrict;
        private String ownerCity;
        private String ownerPincode;

        // PUBLISHER
        private String publisherAddress;
        private String publisherState;
        private String publisherDistrict;
        private String publisherCity;
        private String publisherPincode;

        // EDITOR
        private String editorName;
        private String editorAddress;
        private String editorState;
        private String editorDistrict;
        private String editorCity;
        private String editorPincode;

        // PUBLICATION
        private String publicationAddress;
        private String publicationState;
        private String publicationDistrict;
        private String publicationCity;
        private String publicationPincode;

        // PRINTING PRESS
        private String printerName;
        private String pressName;
        private String pressAddress;
        private String pressState;
        private String pressDistrict;
        private String pressCity;
        private String pressPincode;

//        // DAK SECTION
//        private String dakReceivedDate;
//        private String dakDiaryNo;
//        private String dakState;
//        private String dakDistrict;
//        private String dakSection;
//        private String dakProcessed;
//        private String dakForwardTo;
//
//        // REVISION
//        private String revisionTitleName;
//        private String revisionRegNo;
//        private String revisionReason;
//        private String revisionChanges;
//        private String revisionDate;
    }


    // ── RESPONSE ─────────────────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Response {
        private Long   id;
        private String diaryNumber;
        private String titleName;
        private String regNo;
        private String fileNo;
        private String sectionName;
        private String state;
        private String district;
        private String ownerName;
        private String publisherName;
        private String periodicity;
        private String status;
        private String createdBy;
        private String language;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        // DOCUMENTS
        private String documentName;
        private String documentName2;
        private boolean hasDocument;
        private boolean hasDocument2;
        private String filePath;
        private String filePath2;
        private List<String> documents;
        private List<String> pinCode;

        // OWNER
        private String ownerAddress;
        private String ownerState;
        private String ownerDistrict;
        private String ownerCity;
        private String ownerPincode;

        // PUBLISHER
        private String publisherAddress;
        private String publisherState;
        private String publisherDistrict;
        private String publisherCity;
        private String publisherPincode;

        // EDITOR
        private String editorName;
        private String editorAddress;
        private String editorState;
        private String editorDistrict;
        private String editorCity;
        private String editorPincode;

        // PUBLICATION
        private String publicationAddress;
        private String publicationState;
        private String publicationDistrict;
        private String publicationCity;
        private String publicationPincode;

        // PRINTING PRESS
        private String printerName;
        private String pressName;
        private String pressAddress;
        private String pressState;
        private String pressDistrict;
        private String pressCity;
        private String pressPincode;

//        // DAK SECTION
//        private String dakReceivedDate;
//        private String dakDiaryNo;
//        private String dakState;
//        private String dakDistrict;
//        private String dakSection;
//        private String dakProcessed;
//        private String dakForwardTo;
//
//        // REVISION
//        private String revisionTitleName;
//        private String revisionRegNo;
//        private String revisionReason;
//        private String revisionChanges;
//        private String revisionDate;
    }


    // ── ADVANCED SEARCH REQUEST ───────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class SearchRequest {
        private String diaryNumber;
        private String regNo;
        private String titleName;
        private String fileNo;
        private String sectionName;
        private String state;
        private String district;
        private String ownerName;
        private String publisherName;
        private String periodicity;
        private String status;
    }


    // ── GENERIC API WRAPPER ───────────────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ApiResponse<T> {
        private boolean success;
        private String  message;
        private T       data;
        private Integer count;

        public static <T> ApiResponse<T> ok(String message, T data) {
            return ApiResponse.<T>builder()
                    .success(true).message(message).data(data).build();
        }

        public static <T> ApiResponse<T> error(String message) {
            return ApiResponse.<T>builder()
                    .success(false).message(message).build();
        }
    }

}