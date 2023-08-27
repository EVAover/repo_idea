package com.qiang.service.impl;

import com.qiang.dao.TestMapper;
import com.qiang.domain.Test;
import com.qiang.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Test> findAllTest() {

        List<Test> allTest = testMapper.findAllTest();
        return allTest;
    }
}
