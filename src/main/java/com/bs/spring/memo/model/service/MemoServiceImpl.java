package com.bs.spring.memo.model.service;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {
    private SqlSession session;
    private MemoDao dao;

    public MemoServiceImpl(MemoDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }
    @Override
    public int saveMemo(Memo memo) {
        return dao.saveMemo(session,memo);
    }

    @Override
    public List<Memo> selectMemoList() {
        return dao.selectMemoList(session);
    }
}
