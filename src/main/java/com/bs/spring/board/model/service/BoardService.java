package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dto.Board;


import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Board> selectBoardList(Map<String, Integer> map);
    int insertBoardList(Board board);
    int countBoardList();

}
