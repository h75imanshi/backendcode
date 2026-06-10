package com.eoffice.repository;

import com.eoffice.model.MandatoryFiling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MandatoryFilingRepository
        extends JpaRepository<MandatoryFiling, Long> {

    // ── Simple derived search methods ─────────────────────
    List<MandatoryFiling> findByDiaryNumberContainingIgnoreCase(String v);
    List<MandatoryFiling> findByRegNoContainingIgnoreCase(String v);
    List<MandatoryFiling> findByTitleNameContainingIgnoreCase(String v);
    List<MandatoryFiling> findByFileNoContainingIgnoreCase(String v);
    List<MandatoryFiling> findByStateIgnoreCase(String v);
    List<MandatoryFiling> findByDistrictIgnoreCase(String v);

    // =====================================================VALIDATION
    // =====================================================
    boolean existsByTitleNameIgnoreCase( String titleName );
    boolean existsByRegNoIgnoreCase
            ( String regNo );
    // ── Full dynamic / advanced search ───────────────────
    @Query("""
        SELECT f FROM MandatoryFiling f
        WHERE
            (:diaryNumber IS NULL OR LOWER(f.diaryNumber)    LIKE LOWER(CONCAT('%',:diaryNumber,'%')))
        AND (:regNo       IS NULL OR LOWER(f.regNo)          LIKE LOWER(CONCAT('%',:regNo,'%')))
        AND (:titleName   IS NULL OR LOWER(f.titleName)      LIKE LOWER(CONCAT('%',:titleName,'%')))
        AND (:fileNo      IS NULL OR LOWER(f.fileNo)         LIKE LOWER(CONCAT('%',:fileNo,'%')))
        AND (:sectionName IS NULL OR LOWER(f.sectionName)    = LOWER(:sectionName))
        AND (:state       IS NULL OR LOWER(f.state)          LIKE LOWER(CONCAT('%',:state,'%')))
        AND (:district    IS NULL OR LOWER(f.district)       LIKE LOWER(CONCAT('%',:district,'%')))
        AND (:ownerName   IS NULL OR LOWER(f.ownerName)      LIKE LOWER(CONCAT('%',:ownerName,'%')))
        AND (:publisher   IS NULL OR LOWER(f.publisherName)  LIKE LOWER(CONCAT('%',:publisher,'%')))
        AND (:periodicity IS NULL OR CAST(f.periodicity AS string) = :periodicity)
        AND (:status      IS NULL OR CAST(f.status      AS string) = :status)
        ORDER BY f.id DESC
        """)
    List<MandatoryFiling> advancedSearch(
            @Param("diaryNumber") String diaryNumber,
            @Param("regNo")       String regNo,
            @Param("titleName")   String titleName,
            @Param("fileNo")      String fileNo,
            @Param("sectionName") String sectionName,
            @Param("state")       String state,
            @Param("district")    String district,
            @Param("ownerName")   String ownerName,
            @Param("publisher")   String publisher,
            @Param("periodicity") String periodicity,
            @Param("status")      String status);

    // UNIVERSAL SEARCH
    @Query("""
SELECT f FROM MandatoryFiling f
WHERE
LOWER(f.diaryNumber) = LOWER(:keyword)
OR LOWER(f.regNo) = LOWER(:keyword)
OR LOWER(f.titleName) LIKE LOWER(CONCAT('%',:keyword,'%'))
ORDER BY f.id DESC
""")
    List<MandatoryFiling> universalSearch(
            @Param("keyword") String keyword
    );

}

