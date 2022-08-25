package com.sp.service;


import com.sp.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("1312222222");
        address.setName("好人");
        addressService.addNewAddress(6,"admin",address);
    }

    @Test
    public void getByUid(){
        for (Address address : addressService.getByUid(6)) {
            System.out.println(address);
        }
    }

    @Test
    public void setDefault(){
        addressService.setDefault(1,6,"admin");
    }

    @Test
    public void delete(){
        addressService.delete(1,6,"joker");
    }
}
