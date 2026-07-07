package com.eoffice.implement;

import com.eoffice.dto.RevisionResponseDto;
import com.eoffice.model.Revision;
import com.eoffice.repository.RevisionRepository;
import com.eoffice.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevisionServiceImpl implements RevisionService {

    private final RevisionRepository revisionRepository;

    @Override
    public RevisionResponseDto createRevision(RevisionResponseDto dto) {

        Revision revision = mapToEntity(dto);
        Revision saved = revisionRepository.save(revision);
        return mapToDto(saved);
    }

    @Override
    public List<RevisionResponseDto> getAllRevisions() {
        return revisionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RevisionResponseDto> getByDiaryNumber(String diaryNumber) {
        return revisionRepository.findByDiaryNumber(diaryNumber)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RevisionResponseDto getById(Long id) {
        Revision revision = revisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revision not found"));
        return mapToDto(revision);
    }

    @Override
    public RevisionResponseDto updateRevision(Long id, RevisionResponseDto dto) {

        Revision revision = revisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revision not found"));

        revision.setDiaryNumber(dto.getDiaryNumber());
        revision.setRegNo(dto.getRegNo());
        revision.setTitleName(dto.getTitleName());
        revision.setOwner(dto.getOwner());
        revision.setPublisher(dto.getPublisher());
        revision.setEditor(dto.getEditor());
        revision.setPressDetails(dto.getPressDetails());
        revision.setRemark(dto.getRemark());

        Revision updated = revisionRepository.save(revision);
        return mapToDto(updated);
    }

    @Override
    public void deleteRevision(Long id) {
        revisionRepository.deleteById(id);
    }

    // -------- Mapper Methods --------

    private RevisionResponseDto mapToDto(Revision r) {
        return RevisionResponseDto.builder()
                .id(r.getId())
                .diaryNumber(r.getDiaryNumber())
                .regNo(r.getRegNo())
                .titleName(r.getTitleName())
                .owner(r.getOwner())
                .publisher(r.getPublisher())
                .editor(r.getEditor())
                .pressDetails(r.getPressDetails())
                .remark(r.getRemark())
                .createdAt(r.getCreatedAt())
                .build();
    }

    private Revision mapToEntity(RevisionResponseDto dto) {
        return Revision.builder()
                .diaryNumber(dto.getDiaryNumber())
                .regNo(dto.getRegNo())
                .titleName(dto.getTitleName())
                .owner(dto.getOwner())
                .publisher(dto.getPublisher())
                .editor(dto.getEditor())
                .pressDetails(dto.getPressDetails())
                .remark(dto.getRemark())
                .build();
    }
}