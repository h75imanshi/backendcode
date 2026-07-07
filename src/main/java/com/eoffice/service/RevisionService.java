package com.eoffice.service;

import com.eoffice.dto.RevisionResponseDto;

import java.util.List;

public interface RevisionService {

    RevisionResponseDto createRevision(RevisionResponseDto dto);

    List<RevisionResponseDto> getAllRevisions();

    List<RevisionResponseDto> getByDiaryNumber(String diaryNumber);

    RevisionResponseDto getById(Long id);

    RevisionResponseDto updateRevision(Long id, RevisionResponseDto dto);

    void deleteRevision(Long id);
}