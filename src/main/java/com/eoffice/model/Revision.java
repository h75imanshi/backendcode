package com.eoffice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "revision")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Reference Fields =====
    @Column(name = "diary_number")
    private String diaryNumber;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "title_name")
    private String titleName;

    // ===== Details =====
    private String owner;
    private String publisher;
    private String editor;

    @Column(name = "press_details", columnDefinition = "TEXT")
    private String pressDetails;

    @Column(columnDefinition = "TEXT")
    private String remark;

    // ===== Audit Fields =====
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}