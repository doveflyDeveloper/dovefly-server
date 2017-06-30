package cn.dovefly.AtomicTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dove.zhang on 2017/6/15.
 */

public class IncrementTestDemo {

    public static int count = 0;
    public static Counter counter = new Counter();
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    volatile public static int countVolatile = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        count++;//不安全
                        counter.increment();//可以保证自增正确
                        atomicInteger.getAndIncrement();//可以保证自增正确
                        countVolatile++;//不能保证自增正确
                    }
                }
            }.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("static count: " + count);
        System.out.println("Counter: " + counter.getValue());
        System.out.println("AtomicInteger: " + atomicInteger.intValue());
        System.out.println("countVolatile: " + countVolatile);
    }

}

class Counter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return ++value;
    }

    public synchronized int decrement() {
        return --value;
    }
}
