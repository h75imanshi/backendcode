package com.eoffice.dto;

public interface RegistrationProjection {

    String getDiaryNumber();
    String getRegNo();
    String getTitleName();
    String getPeriodicity();
    String getLanguage();
    String getStatus();
    String getRegistrationDate();

    // Owner
    String getOwnerName();
    String getOwnerEmail();
    String getOwnerContactNo();
    String getOwnerCategory();
    String getOwnerState();
    String getOwnerDistrict();
    String getOwnerAddress();
    String getOwnerPincode();

    // Publisher
    String getPublisherName();
    String getPublisherEmail();
    String getPublisherContactNo();
    String getPublisherState();
    String getPublisherDistrict();
    String getPublisherAddress();
    String getPublisherPincode();

    // Editor
    String getEditorName();
    String getEditorEmail();
    String getEditorContactNo();
    String getEditorState();
    String getEditorDistrict();
    String getEditorAddress();
    String getEditorPincode();

    // Press
    String getPressId();
    String getPressName();
    String getApprovedDate();
    String getPressState();
    String getPressDistrict();
    String getPressAddress();
    String getPressPincode();

    // Keeper
    String getKeeperName();
    String getKeeperEmail();
    String getKeeperContactNo();
    String getKeeperState();
    String getKeeperDistrict();
    String getKeeperAddress();
    String getKeeperPincode();
}