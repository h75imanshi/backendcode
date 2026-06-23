package com.eoffice.repository;

import com.eoffice.dto.NewspaperDto;
import com.eoffice.model.NewspaperDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewspaperRepository
        extends JpaRepository<NewspaperDetails, Integer> {

    @Query(value = """
SELECT
    '' AS oldRegistrationNoNews,

   tr.registration_no AS registrationNoNews,
    tr.title_name AS newspaperTitle,

    JSON_UNQUOTE(
        JSON_EXTRACT(tr.publication_language,'$[0].value')
    ) AS languageNw,

    tr.periodicity AS periodicity,

    tr.publication_address AS ppbAddress,

    s.State_Name AS ppbState,

    s.District_Name AS ppbDistrict,

    tr.publication_pincode AS ppbPincode

FROM title_registration tr

LEFT JOIN state s
       ON tr.publication_state_id = s.State_Code
      AND tr.publication_district_id = s.District_Code

WHERE LOWER(TRIM(tr.press_name))
      = LOWER(TRIM(:pressName))
""", nativeQuery = true)
    List<NewspaperDto> findByPressName(
            @Param("pressName") String pressName);
}