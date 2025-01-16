package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private DemoDao demoDao; //객체를 특정하지 않고 인터페이스를 줌.


    @Override
    public List<Demo> selectDemoList() {
        return demoDao.selectDemoList(sqlSession);
    }

    @Override
    public int insertDemo(Demo demo) {
        return demoDao.insertDemo(sqlSession, demo);
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
