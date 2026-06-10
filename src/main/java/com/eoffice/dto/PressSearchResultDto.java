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

}

@Data
class KeeperDto {
    private String id;
    private String keeperName;
    private String mobile;
    private String email;
    private String state;
    private String district;
    private String address;

}

@Data
class MachineDto {
    private String id;
    private String machineName;
    private String machineType;
    private String model;
    private String serialNo;
    private Integer mfgYear;
    private String capacity;
    private Integer quantity;
    private String condition;
}
