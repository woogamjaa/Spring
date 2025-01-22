package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dao.BoardDao;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.error.MyException;
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


    //Map을 이용하여 처리하는 방식을 예시로 듬
    @Override
    public Board findBoardByNo(int no) {
     // public Map<String,Object> findAttachByNo(int no) {
       // Board board = dao.findBoardByNo(session, no);
        // 1개 게시물에 1개이상 이라서 1대다 관계 이런식으로 가져와도 됨.
        //List<Attachment> files=dao.findAttachByNo(session,no);
        //board.setFiles(files);
        return dao.findBoardByNo(session, no);
       // return Map.of("board",board,"files",files);
    }
}
