package com.example.executor.pool.demo.test;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunx
 * @date 2019-12-02
 * @description
 */
public class Test {
    private static AtomicInteger at = new AtomicInteger(0);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        ExecutorService exec1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), r -> {
            Thread t = new Thread(r);
            t.setName("worker-thread-" + UUID.randomUUID().toString());
            return t;
        }, (r, executor) -> {
            if (!executor.isShutdown()) {
                try {
                    //阻塞等待put操作
                    System.err.println("waiting queue is full, putting..." + r);
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ExecutorService exec2 = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), r -> {
            Thread t = new Thread(r);
            t.setName("worker-thread-" + UUID.randomUUID().toString());
            return t;
        }, (r, executor) -> {
            if (!executor.isShutdown()) {
                try {
                    //阻塞等待put操作
                    System.err.println("waiting queue is full, putting..." + r);
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ExecutorService exec = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), r -> {
            Thread t = new Thread(r);
            t.setName("worker-thread-" + UUID.randomUUID().toString());
            return t;
        },new ThreadPoolExecutor.DiscardOldestPolicy());

        long s = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            exec.submit(() -> {
                System.err.println("Worker" + at.getAndIncrement() + " start.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("Worker end.");
            });
        }

        System.out.println(System.currentTimeMillis() - s);

//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < 1000; i++) {
//            Future<?> future = exec.submit(new ExcuteTask1(i + ""));
//            try {
//                set.add(future.get().toString());
//            } catch (Exception e) {
//
//            }
//        }

//        for (int i = 0; i < 1000; i++) {
//            if(!set.contains(String.valueOf(i))){
//                System.out.println(i+"不存在");
//            }
//        }
//
//
//
//        for (String s : list) {
//            System.out.println(s);
//        }
    }


    static class ExcuteTask1 implements Callable<String> {
        private String taskName;

        public ExcuteTask1(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            try {
//              Java 6/7最佳的休眠方法为TimeUnit.MILLISECONDS.sleep(100);
//              最好不要用
                Thread.sleep(2000);
//                TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));// 1000毫秒以内的随机数，模拟业务逻辑处理
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-------------这里执行业务逻辑，Callable TaskName = " + taskName + "-------------");
            return taskName;
        }
    }
}
