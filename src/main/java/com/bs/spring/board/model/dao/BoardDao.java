package com.bs.spring.board.model.dao;


import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface BoardDao {

    int saveBoard(SqlSession session, Board board);
    List<Board> selectBoardList(SqlSession session);
    int countBoardList(SqlSession session);
}
