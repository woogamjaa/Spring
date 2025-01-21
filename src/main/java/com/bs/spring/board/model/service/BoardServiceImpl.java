package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dao.BoardDao;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.error.MyException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public int insertBoardList(Board board, List<Attachment> files) {
        try {
            int result = dao.insertBoardList(session, board);
            if (files.size() > 0) {
                files.stream().forEach(file -> {
                    file.setBoardNo(board.getBoardNo());
                    dao.insertAttachment(session, file);
                });
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new MyException("게시글 저장실패");

        }
        return 0;
    }
}
