package com.eoffice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "printer_press_details")
@Data
public class Press {

    @Id
    @Column(name = "press_id")
    private String id;

    @Column(name = "press_application_no")
    private String pressApplicationNo;

    @NotBlank(message = "Press Name is required")
    @Size(min = 3, max = 200)

    @Column(name = "press_name")
    private String pressName;

    @Column(name = "press_address")
    private String pressAddress;

    @Column(name = "press_state_id")
    private Integer pressStateId;

    @Column(name = "press_district_id")
    private Integer pressDistrictId;

    @Column(name = "press_state")
    private String state;

    @Column(name = "press_district")
    private String district;

    @Column(name = "press_pincode")
    private String pressPincode;

    @Column(name = "press_unique_registration_no")
    private String regNo;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "keeper_name")
    private String keeperName;

    @Column(name = "keeper_mobile_no")
    private String keeperMobileNo;

    @Column(name = "keeper_email")
    private String keeperEmail;

    @Column(name = "keeper_address")
    private String keeperAddress;

    @Column(name = "keeper_state_id")
    private Integer keeperStateId;

    @Column(name = "keeper_district_id")
    private Integer keeperDistrictId;

    @Column(name = "keeper_pincode")
    private String keeperPincode;

    @Column(name = "press_type", length = 50)
    private String pressType;

    @Column(name = "est_year")
    private Integer estYear;

    @Column(name = "floor_area", length = 50)
    private String floorArea;

    @Column(name = "license_no", length = 100)
    private String licenseNo;

    @Column(name = "license_validity")
    private LocalDate licenseValidity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now(); }

    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }



//    public String getRegNo() {
//
//    }
////
//    public String getState() {
//
//
//    }
//
//    public String getStatus() {
//    }
//
//    public String getRegNo() {
//    }
//
//    public String getAppNo() {
//    }
//
//    public String getDistrict() {
//    }

//    @OneToMany(mappedBy = "press", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Keeper> keepers;
//
//    @OneToMany(mappedBy = "press", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<PressMachine> machines;
}


