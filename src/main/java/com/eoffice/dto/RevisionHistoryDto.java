package com.eoffice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevisionHistoryDto {

    private String fieldName;
    private String oldValue;
    private String newValue;
    private LocalDateTime changedAt;
}