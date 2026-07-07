package com.eoffice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevisionResponseDto {

    private Long id;
    private String diaryNumber;
    private String regNo;
    private String titleName;

    private String owner;
    private String publisher;
    private String editor;
    private String pressDetails;

    private String remark;
    private LocalDateTime createdAt;
}