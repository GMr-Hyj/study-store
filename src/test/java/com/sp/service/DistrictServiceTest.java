package com.sp.service;

import com.sp.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Autowired
    private DistrictService districtService;

    @Test
    public void getByParent(){
        List<District> list = districtService.getByParent("440000");
        for (District district : list) {
            System.out.println(district);
        }
    }

    @Test
    public void getNameByCode() {
        System.out.println(districtService.getNameByCode("440000"));
    }
}
