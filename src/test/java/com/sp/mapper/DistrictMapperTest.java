package com.sp.mapper;

import com.sp.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("86");
        for (District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        System.out.println(districtMapper.findNameByCode("440000"));
    }
}
