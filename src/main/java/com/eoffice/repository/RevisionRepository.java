package com.eoffice.repository;

import com.eoffice.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    List<Revision> findByDiaryNumber(String diaryNumber);

    List<Revision> findByRegNo(String regNo);
}