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
            n.old_registration_no_news AS oldRegistrationNoNews,
            n.registration_no_news AS registrationNoNews,
            n.newspaper_title AS newspaperTitle,
            n.language_nw AS languageNw,
            n.periodicity AS periodicity,
            n.ppb_address AS ppbAddress,
            n.ppb_state AS ppbState,
            n.ppb_district AS ppbDistrict,
            n.ppb_pincode AS ppbPincode
        FROM verified_register_rni_2 n
        WHERE LOWER(TRIM(n.press_name))
              = LOWER(TRIM(:pressName))
        """, nativeQuery = true)
    List<NewspaperDto> findByPressName(
            @Param("pressName") String pressName);

}