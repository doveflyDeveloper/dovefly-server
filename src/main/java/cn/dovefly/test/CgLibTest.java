package cn.dovefly.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by fengchunming on 2017/5/12.
 */
public class CgLibTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor)ctx.getBean("threadPoolTaskExecutor");




    }
}
