package com.bs.spring.board.model.dao;

import com.bs.spring.board.model.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    public List<BoardEntity> findall(EntityManager em, Map<String,Integer> param) {
        String jsql="select b from BoardEntity b";
        TypedQuery<BoardEntity> tquery = em.createQuery(jsql, BoardEntity.class);
        tquery.setFirstResult((param.get("cPage")-1)*param.get("numPerPage"));
        tquery.setMaxResults(param.get("numPerPage"));

        return tquery.getResultList();
//                em.createQuery(jsql,BoardEntity.class).getResultList();
    }
}
