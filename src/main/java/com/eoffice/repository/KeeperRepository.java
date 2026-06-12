package com.eoffice.repository;

import com.eoffice.dto.KeeperDto;
import com.eoffice.model.KeeperDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeeperRepository
        extends JpaRepository<KeeperDetails, String> {
    @Query(value = """
    SELECT
        ppd.keeper_name AS keeperName,
        ppd.keeper_mobile_no AS keeperMobileNo,
        ppd.keeper_email AS keeperEmail,
        ppd.keeper_address AS address,
        s.State_Name AS keeperState,
        s.District_Name AS keeperDistrict,
        ppd.keeper_pincode AS keeperPincode
    FROM printer_press_details ppd
    LEFT JOIN state s
        ON ppd.keeper_district_id = s.District_Code
    WHERE ppd.press_id = :pressId
    """, nativeQuery = true)
    List<KeeperDto> getKeepersByPressId(@Param("pressId") String pressId);
    List<KeeperDetails> findByPressId(String pressId);
}