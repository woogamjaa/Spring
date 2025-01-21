package com.bs.spring.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequestMapping
@Builder
public class Attachment {
    private Long attachmentNo;
    private Long boardNo;
    private String originalFileName;
    private String renamedFileName;
    private Date uploadDate;
    private int downloadCount;
}
