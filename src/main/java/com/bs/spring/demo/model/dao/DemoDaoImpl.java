package com.bs.spring.demo.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DemoDaoImpl implements DemoDao {
    @Override
    public List<Demo> selectDemoList(SqlSession Session) {
        return null;
    }

    @Override
    public int insertDemo(SqlSession Session, Demo demo) {
        return 0;
    }

    @Override
    public int updateDemo(SqlSession Session, Demo demo) {
        return 0;
    }

    @Override
    public int deleteDemo(SqlSession Session, Demo demo) {
        return 0;
    }

    @Override
    public Demo selectDemo(SqlSession Session, int demoNo) {
        return null;
    }
}
