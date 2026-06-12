package com.eoffice.repository;

import com.eoffice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, String> {

    @Query(value = """
        SELECT
            d.*,
            v.*
        FROM documents d
        LEFT JOIN verified_register_rni_2 v
        ON d.reg_no COLLATE utf8mb4_unicode_ci =
           v.registration_no_news COLLATE utf8mb4_unicode_ci
        WHERE
              d.reg_no = :searchText
           OR UPPER(TRIM(d.title_name))
              LIKE CONCAT('%', UPPER(:searchText), '%')
           OR UPPER(TRIM(v.newspaper_title))
              LIKE CONCAT('%', UPPER(:searchText), '%')
        """,
            nativeQuery = true)
    List<Object[]> searchRegistration(
            @Param("searchText") String searchText);
}