package com.bs.spring.board.model.entity;

import com.bs.spring.board.model.dto.Attachment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequestMapping
@Builder

@Entity
@Table(name="ATTACHMENT")
@SequenceGenerator(name="seqAttcachmentNo", sequenceName = "SEQ_ATTACHMENTNO", allocationSize = 1)

public class AttachmentEntity {
    @Id
    @GeneratedValue(generator ="seqAttcachmentNo", strategy = GenerationType.SEQUENCE)
    private Long attachmentNo;
//    private Long boardNo;

    @ManyToOne
    @JoinColumn(name="BOARDNO")
    private BoardEntity board;
    private String originalFileName;
    private String renamedFileName;
    private Date uploadDate;
    private int downloadCount;

    public AttachmentEntity() {

    }

    public Attachment toAttachment() {
        return Attachment.builder()
                .attachmentNo(toAttachment().getAttachmentNo())
                .renamedFileName(toAttachment().getRenamedFileName())
                .uploadDate(toAttachment().getUploadDate())
                .downloadCount(toAttachment().getDownloadCount())
                .build();
    }
}