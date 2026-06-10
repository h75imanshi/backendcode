package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "state")
@Getter
@Setter
public class StateMaster {

    @Id
    @Column(name = "S.No.")
    private Integer sno;

    @Column(name = "State_Code")
    private Integer stateCode;

    @Column(name = "State_Name")
    private String stateName;

    @Column(name = "District_Code")
    private Integer districtCode;

    @Column(name = "District_Name")
    private String districtName;
}