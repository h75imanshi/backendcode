package com.eoffice.repository;

import com.eoffice.dto.RegistrationProjection;
import com.eoffice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query(value = """

SELECT

/* ================= DOCUMENT INFO ================= */
tr.diary_number                         AS diaryNumber,
tr.registration_no                      AS regNo,
tr.title_name                           AS titleName,
p.Periodicity_period                    AS periodicity,
tr.publication_language                 AS language,
tr.status                               AS status,
tr.registration_date                    AS registrationDate,

/* ================= OWNER ================= */
tr.ow_name_english                      AS ownerName,
tr.ow_email                             AS ownerEmail,
tr.ow_mobile_no                         AS ownerContactNo,
tr.ow_category                          AS ownerCategory,
ow.State_Name                           AS ownerState,
ow.District_Name                        AS ownerDistrict,
tr.ow_address                           AS ownerAddress,
tr.ow_pincode                           AS ownerPincode,

/* ================= PUBLISHER ================= */
tr.pb_name_english                      AS publisherName,
tr.pb_email                             AS publisherEmail,
tr.pb_mobile_no                         AS publisherContactNo,
pub.State_Name                          AS publisherState,
pub.District_Name                       AS publisherDistrict,
tr.pb_address                           AS publisherAddress,
tr.pb_pincode                           AS publisherPincode,

/* ================= EDITOR ================= */
tr.ed_name                              AS editorName,
tr.ed_email                             AS editorEmail,
tr.ed_mobile_no                         AS editorContactNo,
ed.State_Name                           AS editorState,
ed.District_Name                        AS editorDistrict,
tr.ed_address                           AS editorAddress,
tr.ed_pincode                           AS editorPincode,

/* ================= PRESS ================= */
tr.press_id                             AS pressId,
tr.press_name                           AS pressName,
tr.approved_date                        AS approvedDate,
prs.State_Name                          AS pressState,
prs.District_Name                       AS pressDistrict,
tr.press_address                        AS pressAddress,
tr.press_pincode                        AS pressPincode,

/* ================= KEEPER ================= */
tr.keeper_name                          AS keeperName,
tr.keeper_email                         AS keeperEmail,
tr.keeper_mobile_no                    AS keeperContactNo,
kp.State_Name                           AS keeperState,
kp.District_Name                        AS keeperDistrict,
tr.keeper_address                       AS keeperAddress,
tr.keeper_pincode                       AS keeperPincode

FROM title_registration tr

/* ================= PERIODICITY ================= */
LEFT JOIN periodicity p
    ON tr.periodicity = p.Periodicity_id

/* ================= OWNER STATE ================= */
LEFT JOIN state ow
    ON tr.ow_state_id = ow.State_Code
   AND tr.ow_district_id = ow.District_Code

/* ================= PUBLISHER STATE ================= */
LEFT JOIN state pub
    ON tr.pb_state_id = pub.State_Code
   AND tr.pb_district_id = pub.District_Code

/* ================= EDITOR STATE ================= */
LEFT JOIN state ed
    ON tr.ed_state_id = ed.State_Code
   AND tr.ed_district_id = ed.District_Code

/* ================= PRESS STATE ================= */
LEFT JOIN state prs
    ON tr.press_state_id = prs.State_Code
   AND tr.press_district_id = prs.District_Code

/* ================= KEEPER STATE ================= */
LEFT JOIN state kp
    ON tr.keeper_state_id = kp.State_Code
   AND tr.keeper_district_id = kp.District_Code

WHERE
    (:value IS NULL OR :value = '')
    OR LOWER(tr.title_name) LIKE LOWER(CONCAT('%', :value, '%'))
    OR LOWER(tr.registration_no) LIKE LOWER(CONCAT('%', :value, '%'))
    OR LOWER(tr.diary_number) LIKE LOWER(CONCAT('%', :value, '%'))

ORDER BY tr.id DESC

""", nativeQuery = true)
    List<RegistrationProjection> searchCompleteData(@Param("value") String value);

}