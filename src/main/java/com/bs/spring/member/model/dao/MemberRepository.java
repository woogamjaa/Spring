package com.bs.spring.member.model.dao;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    public MemberEntity selectMemberById(EntityManager em, String id) {
        return em.find(MemberEntity.class, id);

//        String jsql = "select m from MemberEntity m";
//        jsql = "SELECT m from MemberEntity m WHERE m.userId= :id";
//        return em.createQuery(jsql, Member.class).getResultList();

    }

    public MemberEntity findMemberById(EntityManager em, String id) {
        return em.find(MemberEntity.class, id);
    }

    public boolean insertMember(EntityManager em, MemberEntity m) {
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
