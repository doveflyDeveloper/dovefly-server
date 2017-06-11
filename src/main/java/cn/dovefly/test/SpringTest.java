package cn.dovefly.test;

import cn.dovefly.framework.schema.Animal;
import cn.dovefly.framework.schema.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by fengchunming on 2017/5/12.
 */
public class SpringTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor)ctx.getBean("threadPoolTaskExecutor");
//        threadPoolTaskExecutor.setCorePoolSize(10);
//        System.out.println("=" + threadPoolTaskExecutor.getMaxPoolSize());
//        threadPoolTaskExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("ActiveCount=" + threadPoolTaskExecutor.getActiveCount());
//                    System.out.println("CorePoolSize=" + threadPoolTaskExecutor.getCorePoolSize());
//                    System.out.println("MaxPoolSize=" + threadPoolTaskExecutor.getMaxPoolSize());
//                    System.out.println("PoolSize=" + threadPoolTaskExecutor.getPoolSize());
//                    System.out.println("-------------------------");
//                }
//
//            }
//        });

        for (long i = 0; i < Integer.MAX_VALUE + 1000L; i++) {
            final long d = i;
            threadPoolTaskExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId() + "=" + d);
//                    if (d > Integer.MAX_VALUE) {
//                    }
                }
            });
            System.out.println(i);
            if (i > Integer.MAX_VALUE ) {

                System.out.println("ActiveCount=" + threadPoolTaskExecutor.getActiveCount());
                System.out.println("CorePoolSize=" + threadPoolTaskExecutor.getCorePoolSize());
                System.out.println("MaxPoolSize=" + threadPoolTaskExecutor.getMaxPoolSize());
                System.out.println("PoolSize=" + threadPoolTaskExecutor.getPoolSize());
                System.out.println("-------------------------");

            }
//            System.out.println("=" + threadPoolTaskExecutor.getPoolSize());
//            if (i % 10 == 0) {
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("===================");
        }

    }
}
