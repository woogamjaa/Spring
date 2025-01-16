package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.dto.Demo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DemoServiceImpl implements  DeomService{

    @Override
    public List<Demo> selectDemoList() {
        return null;
    }

    @Override
    public int insertDemo(Demo demo) {
        return 0;
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
