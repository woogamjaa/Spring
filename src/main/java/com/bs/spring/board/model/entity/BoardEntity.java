package com.bs.spring.board.model.entity;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="board")
@SequenceGenerator(name="seqBoardNo",
sequenceName = "SEQ_BOARDNO", allocationSize = 1)
public class BoardEntity {
    @Id
    @GeneratedValue(generator = "seqBoardNo", strategy = GenerationType.SEQUENCE)

    @Column(name="board_no")
    private long boardNo;
    @Column(name="board_title")
    private String boardTitle;
    @Column(name="board_writer")
    private String boardWriter;
    @Column(name="board_content")
    private String boardContent;
//    @ManyToOne
//    @JoinColumn(name="Boardwriter")

    @Column(name="board_date")
    private Date boardDate;
    @Column(name="board_readcount")
    private Integer boardReadCount;

    @OneToMany(mappedBy = "board")
    private List<AttachmentEntity> files= new ArrayList();

    public Board toBoard() {
        return Board.builder()
                .boardNo(boardNo)
                .boardContent(boardContent)
                .boardTitle(boardTitle)
                .boardDate(boardDate)
                .boardWriter(boardWriter)
                .boardReadCount(boardReadCount)
                .build();
    }

    public static BoardEntity fromBoard(Board b) {
        return BoardEntity.builder()
                .boardNo(b.getBoardNo())
                .boardContent(b.getBoardContent())
                .boardTitle(b.getBoardTitle())
                .boardDate(b.getBoardDate())
                .boardWriter(b.getBoardWriter())
                .boardReadCount(b.getBoardReadCount())
                .build();
    }
}
