package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "printer_press_details")
@Data
public class KeeperDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "press_id")
    private String pressId;

    @Column(name = "keeper_name")
    private String keeperName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "district")
    private String district;


}