package com.sp.mapper;

import com.sp.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(6);
        address.setPhone("11111111111");
        address.setName("女人");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        System.out.println(addressMapper.countByUid(6));
    }

    @Test
    public void findByuid(){
        List<Address> list = addressMapper.findByUid(6);
        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(3));
    }

    @Test
    public void updateNonDefault(){
        System.out.println(addressMapper.updateNonDefault(6));
    }

    @Test
    public void updateDefaultByAid(){
        System.out.println(addressMapper.updateDefaultByAid(6, "admin", new Date()));
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(3);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(6));
    }

}
