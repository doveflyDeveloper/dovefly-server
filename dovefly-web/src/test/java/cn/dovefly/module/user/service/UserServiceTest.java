package cn.dovefly.module.user.service;

import cn.dovefly.orm.user.entity.User;
import cn.dovefly.service.user.IUserService;
import cn.dovefly.service.user.impl.UserService;
import cn.dovefly.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class UserServiceTest extends BaseTest {

    @Resource
    private IUserService userService;

    @Test
    public void testQueryById() throws Exception {
        int bookId = 1000;
        System.out.println("--------------------userService"+userService);
        UserService service2 = (UserService) userService;
        System.out.println("=========================="+service2);

        User book = userService.getUserById(bookId);


        System.out.println(book);
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setAge(10);
        user.setUserName("gege");
        user.setPassword("121212");
       userService.add(user);

    }

}