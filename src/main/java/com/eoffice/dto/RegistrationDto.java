package com.eoffice.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String registerNo;

    private String newspaperTitle;
    private String oldRegistrationNoNews;
    private String registrationNoNews;
    private String stateName;
    private String languageNw;
    private String periodicity;

    // Owner
    private String ownerName;
    private String ownerAddress;
    private String ownerState;
    private String ownership;

    // Publisher
    private String publisherName;
    private String publisherNationality;
    private String publisherAddress;

    // Printer
    private String printerName;
    private String printerNationality;
    private String printerAddress;

    // Editor
    private String editorName;
    private String editorNationality;
    private String editorAddress;

    // Place of Publication
    private String ppbAddress;
    private String ppbDistrict;
    private String ppbState;
    private String ppbPincode;

    // Printing Press
    private String pressName;
    private String pressAddress;
    private String pressDistrict;
    private String pressState;
    private String pressPincode;
}