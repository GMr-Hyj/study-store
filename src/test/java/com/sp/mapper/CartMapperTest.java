package com.sp.mapper;

import com.sp.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(22);
        cart.setPid(1000011);
        cart.setNum(5);
        cart.setPrice(1000L);
        System.out.println(cartMapper.insert(cart));
    }

    @Test
    public void updateNumByCid(){
        System.out.println(cartMapper.updateNumByCid(1, 2, "jpker", new Date()));
    }

    @Test
    public void findByUidAndPid(){
        System.out.println(cartMapper.findByUidAndPid(22, 1000011));
    }

    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findVOByUid(6));
    }

    @Test
    public void findVOByCid(){
        Integer[] cids ={1,3,4};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
