package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "printing_press")
@Data
public class MachineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "press_id")
    private String pressId;

    @Column(name = "machine_type")
    private String machineType;

    @Column(name = "model")
    private String model;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "mfg_year")
    private Integer mfgYear;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "power_rating")
    private Integer powerRating;

}