package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dto.Board;


import java.util.List;

public interface BoardService {
    int saveBoard(Board board);
    List<Board> selectBoardList();
    int countBoardList();
}
