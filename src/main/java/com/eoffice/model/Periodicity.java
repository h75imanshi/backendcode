package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "periodicity")
@Data
public class Periodicity {

    @Id
    @Lob
    @Column(name = "Periodicity_id")
    private Integer periodicityId;

    @Column(name = "Periodicity_period")
    private String periodicityPeriod;

    @Column(name = "is_active")
    private Integer isActive;
}