package com.bs.spring.demo.model.dao;

import com.bs.spring.demo.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface DemoDao {
    List<Demo> selectDemoList(SqlSession Session);
    int insertDemo(SqlSession Session,Demo demo);
    int updateDemo(SqlSession Session,Demo demo);
    int deleteDemo(SqlSession Session,Demo demo);
    Demo selectDemo(SqlSession Session, int demoNo);
}
