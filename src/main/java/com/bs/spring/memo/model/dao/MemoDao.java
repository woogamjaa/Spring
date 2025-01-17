package com.bs.spring.memo.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MemoDao {
    int saveMemo(SqlSession session,Memo memo);
    List<Memo> selectMemoList(SqlSession Session);
}
