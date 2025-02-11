package com.bs.spring.demo.model.dao;
import com.bs.spring.demo.model.entity.DemoEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DemoRepository {
    public List<DemoEntity> findAll(EntityManager em) {
        String jpql = "SELECT d FROM DemoEntity d";
        return em.createQuery(jpql, DemoEntity.class).getResultList();
    }
    public boolean insertDemo(EntityManager em, DemoEntity demo) {
        try {
            em.getTransaction().begin();
            em.persist(demo);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
