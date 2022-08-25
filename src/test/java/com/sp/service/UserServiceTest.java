package com.sp.service;

import com.sp.entity.User;
import com.sp.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("test02");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            // 获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            // 获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        System.out.println(userService.login("test03", "123456"));
    }

    @Test
    public void changePassword() {
        userService.changePassword(4,"test03","123456","test");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(4));
    }

    @Test
    public void changInfo(){
        User user = new User();
        user.setPhone("12345678910");
        user.setEmail("123456@qq.com");
        user.setGender(0);
        userService.changeInfo(4,"admin",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(3,"/upload/test.jpg","test");
    }
}
