package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dao.BoardDao;

import com.bs.spring.board.model.dto.Board;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final SqlSession session;
    private final BoardDao dao;


    @Override
    public List<Board> selectBoardList(Map<String, Integer> param) {
        return dao.selectBoardList(session, param);
    }


    @Override
    public int countBoardList() {
        return dao.countBoardList(session);
    }

    @Override
    public int insertBoardList(Board board) {
        return dao.insertBoardList(session,board);
    }
}
