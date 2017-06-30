package cn.dovefly.testExecutor;

import java.util.concurrent.*;

/**
 * Created by dove.zhang on 2017/6/30.
 */
public class ThreadPools {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println();
                return "";
            }
        };


        for (int i = 0; i < 100; i++) {
            try {
                Future<String> taskFuture = exec.submit(task);
                System.out.println(taskFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}