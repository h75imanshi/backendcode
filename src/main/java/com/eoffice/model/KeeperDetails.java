package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "printer_press_details")
@Data
public class KeeperDetails {

    @Id
    private String id;

    @Column(name = "press_id")
    private String pressId;

    @Column(name = "keeper_name")
    private String keeperName;

    @Column(name = "keeper_mobile_no")
    private String keeperMobileNo;

    @Column(name = "keeper_email")
    private String keeperEmail;

    @Column(name = "keeper_address")
    private String address;

    @Column(name = "keeper_state_id")
    private Integer keeperStateId;

    @Column(name = "keeper_district_id")
    private Integer keeperDistrictId;

    @Column(name = "keeper_pincode")
    private String keeperPincode;
}