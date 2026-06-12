package com.eoffice.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;



// ---- Search result row (lightweight) ----
@Data
public class PressSearchResultDto {
    private  String id;
    private String appNo;
    private String regNo;
    private String pressName;
    private String pressType;
    private String printerName;   // first keeper name
    private String state;
    private String district;
    private String status;
    private  String pressAddress;
    private String pressPincode;
    private String keeperName;
    private String keeperMobileNo;
    private String keeperEmail;
    private String keeperAddress;
    private String keeperState;
    private String keeperDistrict;
    private String keeperPincode;


}

@Data
class MachineDto {
    private String id;
    private String machineType;
    private String model;
    private String serialNo;
    private Integer mfgYear;
    private String capacity;
    private Integer powerRating;
    private String condition;

}
