package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;


import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Board> selectBoardList(Map<String, Integer> map);
    int insertBoardList(Board board, List<Attachment> files);
    int countBoardList();

}
