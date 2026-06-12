package com.eoffice.repository;

import com.eoffice.model.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateRepository extends JpaRepository<StateMaster, Long> {

    @Query(value = """
        SELECT DISTINCT State_Name
        FROM state
        ORDER BY State_Name
        """, nativeQuery = true)
    List<String> getStates();

    @Query(value = """
        SELECT District_Name
        FROM state
        WHERE State_Name = :stateName
        ORDER BY District_Name
        """, nativeQuery = true)
    List<String> getDistrictsByState(@Param("stateName") String stateName);

    @Query(value = """
        SELECT State_Name
        FROM state
        WHERE State_Code = :stateCode
        LIMIT 1
        """, nativeQuery = true)
    String getStateName(@Param("stateCode") Integer stateCode);

//    @Query(value = """
//        SELECT District_Name
//        FROM state
//        WHERE State_Code = :stateCode
//        AND District_Code = :districtCode
//        LIMIT 1
//        """, nativeQuery = true)
//    String getDistrictName(
//            @Param("stateCode") Integer stateCode,
//            @Param("districtCode") Integer districtCode

    @Query(value = """
    SELECT District_Name
    FROM state
    WHERE District_Code = :districtCode
    LIMIT 1
    """, nativeQuery = true)
    String getDistrictName(
            @Param("districtCode") Integer districtCode
    );
}