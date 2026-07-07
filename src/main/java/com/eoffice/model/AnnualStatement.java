package com.eoffice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "annual_statement")
@Data
public class AnnualStatement {

    @Id
    @Lob
    private String id;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "periodicity")
    private String periodicity;

    @Column(name = "statement_year")
    private String statementYear;

    @Column(name = "penalty_due")
    private Integer penaltyDue;

    @Column(name = "penalty_paid")
    private Integer penaltyPaid;

    @Column(name = "e_filing")
    private String eFiling;

    @Column(name = "within_due_date")
    private String withinDueDate;
}
