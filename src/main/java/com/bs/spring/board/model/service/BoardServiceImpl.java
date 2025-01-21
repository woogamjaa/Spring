package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dao.BoardDao;

import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSession session;

    @Autowired
    private BoardDao dao; //객체를 특정하지 않고 인터페이스를 줌.

    public BoardServiceImpl(BoardDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public int saveBoard(Board board) {
        return dao.saveBoard(session,board);
    }

    @Override
    public List<Board> selectBoardList() {
        return dao.selectBoardList(session);
    }

    @Override
    public int countBoardList() {
        return dao.countBoardList(session);
    }
}
