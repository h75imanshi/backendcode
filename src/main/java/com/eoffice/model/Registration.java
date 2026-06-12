package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "verified_register_rni_2")
@Data
public class Registration {

    @Id
    @Column(name = "register_no")
    private String registerNo;

    @Column(name = "newspaper_title")
    private String newspaperTitle;

    @Column(name = "old_registration_no_news")
    private String oldRegistrationNoNews;

    @Column(name = "registration_no_news")
    private String registrationNoNews;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "language_nw")
    private String languageNw;

    @Column(name = "periodicity")
    private String periodicity;

    // Publisher
    @Column(name = "pb_name")
    private String publisherName;

    @Column(name = "pb_nationality")
    private String publisherNationality;

    @Column(name = "pb_address")
    private String publisherAddress;

    // Printer
    @Column(name = "pr_name")
    private String printerName;

    @Column(name = "pr_nationality")
    private String printerNationality;

    @Column(name = "pr_address")
    private String printerAddress;

    // Editor
    @Column(name = "ed_name")
    private String editorName;

    @Column(name = "ed_nationality")
    private String editorNationality;

    @Column(name = "ed_address")
    private String editorAddress;

    // Press
    @Column(name = "press_name")
    private String pressName;

    @Column(name = "press_address")
    private String pressAddress;

    @Column(name = "press_district")
    private String pressDistrict;

    @Column(name = "press_state")
    private String pressState;

    @Column(name = "press_pincode")
    private String pressPincode;

    // Place of Publication
    @Column(name = "ppb_address")
    private String placeAddress;

    @Column(name = "ppb_district")
    private String placeDistrict;

    @Column(name = "ppb_state")
    private String placeState;

    @Column(name = "ppb_pincode")
    private String placePincode;

    // Owner
    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_address")
    private String ownerAddress;

    @Column(name = "owner_state")
    private String ownerState;

    @Column(name = "ownership")
    private String ownership;
}