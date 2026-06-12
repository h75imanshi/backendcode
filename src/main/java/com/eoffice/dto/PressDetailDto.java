package com.eoffice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

// ---- Full detail DTO (for popup) ----
@Data
public class PressDetailDto {
    private String id;
    private String appNo;
    private String regNo;
    private String pressName;
    private String pressType;
    private Integer estYear;
    private String floorArea;
    private String licenseNo;
    private LocalDate licenseValidity;
    private Integer pressStateId;
    private Integer pressDistrictId;
    private String status;
    private String diaryNo;
    private List<KeeperDto> keepers;
    private List<MachineDto> machines;
    private String applicationStatus;
    private String pressState;
    private String pressDistrict;
    private String state;
    private String district;
    private String pressAddress;
    private String pressPincode;
    private String keeperName;
    private String keeperMobileNo;
    private String keeperEmail;
    private String keeperState;
    private String keeperDistrict;
    private String keeperPincode;
    private String keeperAddress;



}
