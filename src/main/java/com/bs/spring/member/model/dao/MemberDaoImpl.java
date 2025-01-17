package com.bs.spring.member.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.member.model.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Override
    public Member findMemberById(SqlSession session, String id) {
        return session.selectOne("member.findMemberById", id);
    }

    @Override
    public int saveMember(SqlSession session, Member member)  {
        return session.insert("member.saveMember", member);
    }
}
