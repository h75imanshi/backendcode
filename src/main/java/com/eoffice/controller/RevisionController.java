package com.eoffice.controller;

import com.eoffice.dto.RevisionResponseDto;
import com.eoffice.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revision")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RevisionController {

    private final RevisionService revisionService;

    @PostMapping
    public RevisionResponseDto create(@RequestBody RevisionResponseDto dto) {
        return revisionService.createRevision(dto);
    }

    @GetMapping
    public List<RevisionResponseDto> getAll() {
        return revisionService.getAllRevisions();
    }

    @GetMapping("/{id}")
    public RevisionResponseDto getById(@PathVariable Long id) {
        return revisionService.getById(id);
    }

    @GetMapping("/diary/{diaryNumber}")
    public List<RevisionResponseDto> getByDiary(@PathVariable String diaryNumber) {
        return revisionService.getByDiaryNumber(diaryNumber);
    }

    @PutMapping("/{id}")
    public RevisionResponseDto update(@PathVariable Long id,
                                      @RequestBody RevisionResponseDto dto) {
        return revisionService.updateRevision(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        revisionService.deleteRevision(id);
        return "Revision deleted successfully";
    }
}