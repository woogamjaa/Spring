package com.bs.spring.member.model.service;


import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dao.MemberRepository;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {


    private SqlSession session;
    private MemberDao dao;
    private EntityManager entityManager;
    private MemberRepository repository;

    @Autowired
    public MemberServiceImpl(MemberDao dao, SqlSession session, MemberRepository repository,EntityManager entityManager) {
        this.session = session;
        this.repository = repository;
        this.dao = dao;
        this.entityManager = entityManager;
    }

    @Override
    public Member selectMemberById(String id) {
        return repository.findMemberById(entityManager,id).toMember();
//        return dao.findMemberById(session,id);
    }

    @Override
    public int saveMember(Member member) {
        boolean result=repository.insertMember(entityManager, MemberEntity.fromMember(member));
        return result ? 1 : 0;
//        return dao.saveMember(session,member);
    }

}
