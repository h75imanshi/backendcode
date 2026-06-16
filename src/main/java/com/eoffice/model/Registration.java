package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "title_registration")
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diary_number")
    private String diaryNumber;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "language")
    private String language;

    @Column(name = "periodicity")
    private String periodicity;

    @Column(name = "status")
    private String status;

    // Owner

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_email")
    private String ownerEmail;

    @Column(name = "owner_contact_no")
    private String ownerContactNo;

    @Column(name = "owner_category")
    private String ownerCategory;

    @Column(name = "owner_address")
    private String ownerAddress;

    @Column(name = "owner_state")
    private Integer ownerState;

    @Column(name = "owner_district")
    private Integer ownerDistrict;

    @Column(name = "owner_pincode")
    private String ownerPincode;

    // Publisher

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "publisher_email")
    private String publisherEmail;

    @Column(name = "publisher_contact_no")
    private String publisherContactNo;

    @Column(name = "publisher_address")
    private String publisherAddress;

    @Column(name = "publisher_state")
    private Integer publisherState;

    @Column(name = "publisher_district")
    private Integer publisherDistrict;

    @Column(name = "publisher_pincode")
    private String publisherPincode;

    // Editor

    @Column(name = "editor_name")
    private String editorName;

    @Column(name = "editor_email")
    private String editorEmail;

    @Column(name = "editor_contact_no")
    private String editorContactNo;

    @Column(name = "editor_address")
    private String editorAddress;

    @Column(name = "editor_state")
    private Integer editorState;

    @Column(name = "editor_district")
    private Integer editorDistrict;

    @Column(name = "editor_pincode")
    private String editorPincode;

    // Press

    @Column(name = "press_id")
    private String pressId;

    @Column(name = "press_name")
    private String pressName;

    @Column(name = "press_address")
    private String pressAddress;

    @Column(name = "press_state_id")
    private Integer pressStateId;

    @Column(name = "press_district_id")
    private Integer pressDistrictId;

    @Column(name = "press_pincode")
    private String pressPincode;

    @Column(name = "approved_date")
    private String approvedDate;

    // Keeper

    @Column(name = "keeper_name")
    private String keeperName;

    @Column(name = "keeper_email")
    private String keeperEmail;

    @Column(name = "keeper_mobile_no")
    private String keeperMobileNo;

    @Column(name = "keeper_address")
    private String keeperAddress;

    @Column(name = "keeper_state_id")
    private Integer keeperStateId;

    @Column(name = "keeper_district_id")
    private Integer keeperDistrictId;

    @Column(name = "keeper_pincode")
    private String keeperPincode;
}