package com.bs.spring.board.model.dao;

import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Override
    public int saveBoard(SqlSession session, Board board) {
        return session.insert("board.saveBoard", board);
    }

    @Override
    public List<Board> selectBoardList(SqlSession Session) {
        return Session.selectList("board.selectBoardList");
    }

    @Override
    public int countBoardList(SqlSession session) {
        return session.selectOne("board.countBoardList");
    }
}
