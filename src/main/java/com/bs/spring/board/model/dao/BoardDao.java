package com.bs.spring.board.model.dao;


import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface BoardDao {

    List<Board> selectBoardList(SqlSession session, Map<String, Integer> params);
    int insertBoard(SqlSession session, Board board);
    int countBoardList(SqlSession session);
}
