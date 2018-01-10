package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestTest {
    @Autowired
    private com.imooc.sell.controller.Test test;


    @Test
    public void test1() throws Exception {
      String result=  test.test("b27365a02955fe5c177b1533ada464185756b3e1","1515569769","1577086521","15815291808551267949");
        log.info("[result=]"+result);
    }

}