package com.bs.spring.member.model.service;


import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private SqlSession session;
    private MemberDao dao;

    public MemberServiceImpl(MemberDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public Member selectMemberById(String id) {
        return dao.findMemberById(session,id);
    }

    @Override
    public int saveMember(Member member) {
        return dao.saveMember(session,member);
    }

}
