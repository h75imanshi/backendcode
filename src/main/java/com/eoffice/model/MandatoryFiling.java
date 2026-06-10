package com.eoffice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "documents",
        indexes = {
                @Index(name = "idx_diary",     columnList = "diary_number"),
                @Index(name = "idx_reg",       columnList = "reg_no"),
                @Index(name = "idx_title",     columnList = "title_name"),
                @Index(name = "idx_owner",     columnList = "owner_name"),
                @Index(name = "idx_publisher", columnList = "publisher_name"),
                @Index(name = "idx_file_no",   columnList = "file_no"),
                @Index(name = "idx_state",     columnList = "state"),
                @Index(name = "idx_district",  columnList = "district")
        })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MandatoryFiling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Core Search Fields ────────────────────────────────
    @NotBlank(message = "Diary number is mandatory")
    @Column(name = "diary_number", nullable = false, unique = true, length = 50)
    private String diaryNumber;

    @NotBlank(message = "Title name is mandatory")
    @Column(name = "title_name", nullable = false, length = 255)
    private String titleName;

    @Column(name = "reg_no",       length = 50)
    private String regNo;

    @Column(name = "file_no",      length = 100)   // e.g. C-7-1-MUC-2018
    private String fileNo;

    @Column(name = "section_name", length = 50)    // e.g. R1
    private String sectionName;

    // ── Location ──────────────────────────────────────────
    @Column(name = "state",    nullable = false, length = 100)
    private String state;

    @Column(name = "district", length = 100)
    private String district;

    // ── Owner / Publisher ────────────────────────────────
    @Column(name = "owner_name",     length = 255)
    private String ownerName;

    @Column(name = "publisher_name", length = 255)
    private String publisherName;

    // ── Periodicity ───────────────────────────────────────
    @Enumerated(EnumType.STRING)
    @Column(name = "periodicity", length = 20)
    private Periodicity periodicity;

    // ── Primary Scanned PDF ───────────────────────────────
    @Column(name = "document_path",     length = 500)  // full Windows path on server
    private String filePath;

    @Column(name = "document_name", length = 255)
    private String documentName;

    @Column(name = "document_type", length = 50)
    private String documentType;

    // ── Secondary Scanned PDF ────────────────────────────
    @Column(name = "document_path2",     length = 500)
    private String filePath2;

    @Column(name = "document_type2", length = 50)
    private String documentType2;

    @Column(name = "document_name2", length = 255)
    private String documentName2;

    @Column(name = "created_by")
    private String createdBy;
    // ── Status & Audit ────────────────────────────────────
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



// ── Language ──────────────────────────────────────────

    @Column(name = "language", length = 100)
    private String language;

// ── Pin Code ──────────────────────────────────────────

    @Column(name = "pin_code", length = 20)
    private String pinCode;

    public List<String> getPinCodes() {
        return Collections.singletonList(pinCode);
    }

    public void setPinCodes(String pinCodes) {
        this.pinCode = pinCodes;
    }

//    public void setDocumentType2(String s) {
//        return;
//    }


    @Column(name = "owner_address")
    private String ownerAddress;

    @Column(name = "owner_state")
    private String ownerState;

    @Column(name = "owner_district")
    private String ownerDistrict;

    @Column(name = "owner_city")
    private String ownerCity;

    @Column(name = "owner_pincode")
    private String ownerPincode;



    @Column(name = "publisher_address")
    private String publisherAddress;

    @Column(name = "publisher_state")
    private String publisherState;

    @Column(name = "publisher_district")
    private String publisherDistrict;

    @Column(name = "publisher_city")
    private String publisherCity;

    @Column(name = "publisher_pincode")
    private String publisherPincode;

    @Column(name = "editor_name")
    private String editorName;

    @Column(name = "editor_address")
    private String editorAddress;

    @Column(name = "editor_state")
    private String editorState;

    @Column(name = "editor_district")
    private String editorDistrict;

    @Column(name = "editor_city")
    private String editorCity;

    @Column(name = "editor_pincode")
    private String editorPincode;

    @Column(name = "publication_address")
    private String publicationAddress;

    @Column(name = "publication_state")
    private String publicationState;

    @Column(name = "publication_district")
    private String publicationDistrict;

    @Column(name = "publication_city")
    private String publicationCity;

    @Column(name = "publication_pincode")
    private String publicationPincode;

    @Column(name = "printer_name")
    private String printerName;

    @Column(name = "press_name")
    private String pressName;

    @Column(name = "press_address")
    private String pressAddress;

    @Column(name = "press_state")
    private String pressState;

    @Column(name = "press_district")
    private String pressDistrict;

    @Column(name = "press_city")
    private String pressCity;

    @Column(name = "press_pincode")
    private String pressPincode;

    @Column(name = "pdf1")
    private String pdf1;

    @Column(name = "pdf2")
    private String pdf2;

    @Column(name = "pdf3")
    private String pdf3;

    @Column(name = "pdf4")
    private String pdf4;

    @Column(name = "pdf5")
    private String pdf5;

    @Column(name = "pdf6")
    private String pdf6;

    @Column(name = "pdf7")
    private String pdf7;

    @Column(name = "pdf8")
    private String pdf8;

    @Column(name = "pdf9")
    private String pdf9;

    @Column(name = "pdf10")
    private String pdf10;

//    // ── DAK Section ───────────────────────────────────────
//    @Column(name = "dak_received_date", length = 50)
//    private String dakReceivedDate;
//
//    @Column(name = "dak_diary_no", length = 100)
//    private String dakDiaryNo;
//
//    @Column(name = "dak_state", length = 100)
//    private String dakState;
//
//    @Column(name = "dak_district", length = 100)
//    private String dakDistrict;
//
//    @Column(name = "dak_section", length = 100)
//    private String dakSection;
//
//    @Column(name = "dak_processed", length = 255)
//    private String dakProcessed;
//
//    @Column(name = "dak_forward_to", length = 255)
//    private String dakForwardTo;
//    // ── REVISION ─────────────────────────────────────────
//    @Column(name = "revision_title_name", length = 255)
//    private String revisionTitleName;
//
//    @Column(name = "revision_reg_no", length = 100)
//    private String revisionRegNo;
//
//    @Column(name = "revision_reason", length = 500)
//    private String revisionReason;
//
//    @Column(name = "revision_changes", length = 500)
//    private String revisionChanges;
//
//    @Column(name = "revision_date", length = 50)
//    private String revisionDate;
    // ── Enums ─────────────────────────────────────────────
    public enum Periodicity {
        DAILY, WEEKLY, FORTNIGHTLY, MONTHLY, QUARTERLY, ANNUALLY, IRREGULAR
    }

    public enum Status {
        ACTIVE, INACTIVE, PENDING
    }
}