package com.sp.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void findHotList(){
        System.out.println(productMapper.findHotList());
    }
}
