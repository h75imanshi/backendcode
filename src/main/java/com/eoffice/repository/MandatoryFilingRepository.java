package com.eoffice.repository;

import com.eoffice.dto.RegistrationProjection;
import com.eoffice.model.MandatoryFiling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MandatoryFilingRepository
        extends JpaRepository<MandatoryFiling, Long> {

    // Search methods
    List<MandatoryFiling> findByDiaryNumberContainingIgnoreCase(String diaryNumber);

    List<MandatoryFiling> findByRegNoContainingIgnoreCase(String regNo);

    List<MandatoryFiling> findByTitleNameContainingIgnoreCase(String titleName);
    List<MandatoryFiling> findBySpecifyDetailsContainingIgnoreCase(String specifyDetails);

    List<MandatoryFiling> findByOtherTypeContainingIgnoreCase(String otherType);
    List<MandatoryFiling> findByFileNoContainingIgnoreCase(String fileNo);
    @Query(value = """
        SELECT
            d.diary_number AS diaryNumber,
            d.reg_no AS regNo,
            d.title_name AS titleName,
            d.periodicity AS periodicity,
            d.language AS language,
            d.status AS status,
            DATE_FORMAT(d.created_at, '%d %b %Y') AS registrationDate,

            d.owner_name AS ownerName,
            d.owner_email AS ownerEmail,
            d.owner_contact_no AS ownerContactNo,
            d.owner_category AS ownerCategory,
            os.State_Name AS ownerState,
            os.District_Name AS ownerDistrict,
            d.owner_address AS ownerAddress,
            d.owner_pincode AS ownerPincode,

            d.publisher_name AS publisherName,
            d.publisher_email AS publisherEmail,
            d.publisher_contact_no AS publisherContactNo,
            ps.State_Name AS publisherState,
            ps.District_Name AS publisherDistrict,
            d.publisher_address AS publisherAddress,
            d.publisher_pincode AS publisherPincode,

            d.editor_name AS editorName,
            d.editor_email AS editorEmail,
            d.editor_contact_no AS editorContactNo,
            es.State_Name AS editorState,
            es.District_Name AS editorDistrict,
            d.editor_address AS editorAddress,
            d.editor_pincode AS editorPincode,

            ppd.press_id AS pressId,
            ppd.press_name AS pressName,
            DATE_FORMAT(ppd.approved_date, '%d %b %Y') AS approvedDate,
            prs.State_Name AS pressState,
            prs.District_Name AS pressDistrict,
            ppd.press_address AS pressAddress,
            ppd.press_pincode AS pressPincode,

            ppd.keeper_name AS keeperName,
            ppd.keeper_email AS keeperEmail,
            ppd.keeper_mobile_no AS keeperContactNo,
            ks.State_Name AS keeperState,
            ks.District_Name AS keeperDistrict,
            ppd.keeper_address AS keeperAddress,
            ppd.keeper_pincode AS keeperPincode

        FROM documents d

        LEFT JOIN state os
            ON d.owner_state = os.State_Code
           AND d.owner_district = os.District_Code

        LEFT JOIN state ps
            ON d.publisher_state = ps.State_Code
           AND d.publisher_district = ps.District_Code

        LEFT JOIN state es
            ON d.editor_state = es.State_Code
           AND d.editor_district = es.District_Code

        LEFT JOIN printer_press_details ppd
            ON LOWER(TRIM(d.press_name)) = LOWER(TRIM(ppd.press_name))

        LEFT JOIN state prs
            ON ppd.press_state_id = prs.State_Code
           AND ppd.press_district_id = prs.District_Code

        LEFT JOIN state ks
            ON ppd.keeper_state_id = ks.State_Code
           AND ppd.keeper_district_id = ks.District_Code

        WHERE d.id = :id
        LIMIT 1
        """, nativeQuery = true)
    RegistrationProjection getMoreDetailsById(@Param("id") Long id);
}