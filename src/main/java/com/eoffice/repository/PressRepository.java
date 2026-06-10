package com.eoffice.repository;

import com.eoffice.dto.PressDetailDto;
import com.eoffice.dto.PressSearchResultDto;
import com.eoffice.model.Press;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressRepository
        extends JpaRepository<Press, String> {

    // Search by Press Name
    List<Press> findByPressNameContainingIgnoreCase(
            String pressName
    );

    // Search by Application Number
    List<Press> findByPressApplicationNoContainingIgnoreCase(
            String appNo
    );

    // Search by Keeper Name
    List<Press> findByKeeperNameContainingIgnoreCase(
            String keeperName
    );

    // Search by Status
    List<Press> findByApplicationStatus(
            String status
    );

    // Custom Search Query
//    @Query("""
//        SELECT p
//        FROM Press p
//        WHERE LOWER(p.pressName)
//        LIKE LOWER(CONCAT('%', :name, '%'))
//    """)
//    List<Press> searchByName(
//            @Param("name") String name
//    );


    @Query(value = """
SELECT
    p.press_id AS id,
    p.press_name AS pressName,
    p.press_application_no AS appNo,
    p.press_unique_registration_no AS regNo,
    p.press_type AS pressType,
    s.State_Name AS state,
    s.District_Name AS district,
    p.application_status AS status
FROM printer_press_details p
LEFT JOIN state s
       ON s.State_Code = p.press_state_id
      AND s.District_Code = p.press_district_id
WHERE p.press_id = :id
""", nativeQuery = true)
    PressDetailDto getPressDetail(@Param("id") String id);
}