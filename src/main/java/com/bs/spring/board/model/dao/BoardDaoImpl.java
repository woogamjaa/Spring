package com.bs.spring.board.model.dao;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Override
    public List<Board> selectBoardList(SqlSession Session, Map<String, Integer> param) {
        Integer cPage = param.get("cPage");
        Integer numPerPage = param.get("numPerPage");
        RowBounds rowBounds
                = new RowBounds((cPage-1) * numPerPage, numPerPage);
        return Session.selectList("board.selectBoardList", null,rowBounds);
    }

    @Override
    public int countBoardList(SqlSession session) {
        return session.selectOne("board.countBoardList");
    }

    @Override
    public int insertBoardList(SqlSession session, Board board) {
        return session.insert("board.insertBoardList", board);
    }

    @Override
    public int insertAttachment(SqlSession session, Attachment attachment) {
        return session.insert("board.insertAttachment", attachment);
    }

    @Override
    public Board findBoardByNo(SqlSession session, int no) {
        return session.selectOne("board.findBoardByNo", no);
    }
}
