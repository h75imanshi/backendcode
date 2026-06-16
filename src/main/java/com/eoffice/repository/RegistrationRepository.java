package com.eoffice.repository;

import com.eoffice.dto.RegistrationProjection;
import com.eoffice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository
        extends JpaRepository<Registration, String> {

    @Query(value = """
    SELECT

    tr.register_id                 AS registerId,
    tr.title_name                  AS titleName,
    p.Periodicity_period           AS periodicity,
    tr.publication_language        AS publicationLanguage,

    -- Publisher
    tr.pb_name_english             AS publisherName,
    tr.pb_address                  AS publisherAddress,
    pub.State_Name                 AS publisherState,
    pub.District_Name              AS publisherDistrict,
    tr.pb_pincode                  AS publisherPincode,
    tr.pb_nationality              AS publisherNationality,

    -- Printer
    tr.pr_name                     AS printerName,
    tr.pr_address                  AS printerAddress,
    pr.State_Name                  AS printerState,
    pr.District_Name               AS printerDistrict,
    tr.pr_pincode                  AS printerPincode,
    tr.pr_nationality              AS printerNationality,

    -- Editor
    tr.ed_name                     AS editorName,
    tr.ed_address                  AS editorAddress,
    ed.State_Name                  AS editorState,
    tr.ed_city_name                AS editorCityName,
    ed.District_Name               AS editorDistrict,
    tr.ed_pincode                  AS editorPincode,
    tr.ed_nationality              AS editorNationality,

    -- Press
    tr.press_name                  AS pressName,
    tr.press_address               AS pressAddress,
    prs.State_Name                 AS pressState,
    tr.press_city_name             AS pressCityName,
    prs.District_Name              AS pressDistrict,
    tr.press_pincode               AS pressPincode,

    -- Publication
    tr.publication_name            AS publicationName,
    tr.publication_address         AS publicationAddress,
    pb.State_Name                  AS publicationState,
    tr.publication_city_name       AS publicationCityName,
    pb.District_Name               AS publicationDistrict,
    tr.publication_pincode         AS publicationPincode,

    -- Owner
    tr.ow_name_english             AS ownerName,
    tr.ow_address                  AS ownerAddress,
    ow.State_Name                  AS ownerState,
    tr.ow_city_name                AS ownerCityName,
    ow.District_Name               AS ownerDistrict,
    tr.ow_pincode                  AS ownerPincode

    FROM title_registration tr

    LEFT JOIN periodicity p
           ON tr.periodicity = p.Periodicity_id

    LEFT JOIN state pub
           ON tr.pb_state_id = pub.State_Code
          AND tr.pb_district_id = pub.District_Code

    LEFT JOIN state pr
           ON tr.pr_state_id = pr.State_Code
          AND tr.pr_district_id = pr.District_Code

    LEFT JOIN state ed
           ON tr.ed_state_id = ed.State_Code
          AND tr.ed_district_id = ed.District_Code

    LEFT JOIN state prs
           ON tr.press_state_id = prs.State_Code
          AND tr.press_district_id = prs.District_Code

    LEFT JOIN state pb
           ON tr.publication_state_id = pb.State_Code
          AND tr.publication_district_id = pb.District_Code

    LEFT JOIN state ow
           ON tr.ow_state_id = ow.State_Code
          AND tr.ow_district_id = ow.District_Code

    WHERE
        tr.title_name LIKE CONCAT('%', :value, '%')
        OR CAST(tr.register_id AS CHAR) LIKE CONCAT('%', :value, '%')

    """, nativeQuery = true)
    List<RegistrationProjection> searchCompleteData(
            @Param("value") String value);
}