package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.dao.DemoRepository;
import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.model.entity.DemoEntity;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private DemoDao demoDao; //객체를 특정하지 않고 인터페이스를 줌.
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DemoRepository demoRepository;


    @Override
    public List<Demo> selectDemoList() {
       return demoRepository.findAll(entityManager).stream()
               .map(demoEntity -> demoEntity.toDemo())
               .collect(Collectors.toList());
//        return demoDao.selectDemoList(sqlSession);
    }

    @Override
    public int insertDemo(Demo demo) {
      boolean result = demoRepository.insertDemo(entityManager, DemoEntity.fromDemo(demo));
//        return demoDao.insertDemo(sqlSession, demo);
        return result ? 1 : 0;
    }

    @Override
    public int updateDemo(Demo demo) {
        return 0;
    }

    @Override
    public int deleteDemo(Demo demo) {
        return 0;
    }

    @Override
    public Demo selectDemoById(int demoNo) {
        return null;
    }
}
