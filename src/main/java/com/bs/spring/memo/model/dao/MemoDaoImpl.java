package com.bs.spring.memo.model.dao;

import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoDaoImpl implements MemoDao {
    @Override
    public int saveMemo(SqlSession session,Memo memo) {
        return session.insert("memo.saveMemo", memo);
    }

    @Override
    public List<Memo> selectMemoList(SqlSession Session) {
        return Session.selectList("memo.selectMemoList");
    }
}
