package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "newspaper_details")
@Data
public class NewspaperDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regNo;

    private String newspaperName;

    private String placeOfPublication;

    private String state;

    private String district;

    private String pincode;

    private String language;

    private String periodicity;
}