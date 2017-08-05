package cn.dovefly.module.user.service;

import cn.dovefly.orm.user.entity.User;
import cn.dovefly.service.user.IUserService;
import cn.dovefly.service.user.impl.UserService;
import cn.dovefly.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService2Test extends BaseTest {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("xxxxxxxxxxx");
                return null;
            }
        });
    }



}