package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "verified_register_rni_2")
@Data
public class NewspaperDetails {

    @Id
    private Integer id;

    @Column(name = "press_name")
    private String pressName;

    @Column(name = "old_registration_no_news")
    private String oldRegistrationNoNews;

    @Column(name = "registration_no_news")
    private String registrationNoNews;

    @Column(name = "newspaper_title")
    private String newspaperTitle;

    @Column(name = "language_nw")
    private String languageNw;

    @Column(name = "periodicity")
    private String periodicity;

    @Column(name = "ppb_address")
    private String ppbAddress;

    @Column(name = "ppb_state")
    private String ppbState;

    @Column(name = "ppb_district")
    private String ppbDistrict;

    @Column(name = "ppb_pincode")
    private String ppbPincode;
}