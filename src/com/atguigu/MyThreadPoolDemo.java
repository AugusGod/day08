package com.atguigu;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static  void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()

        );

        try{
            for (int i = 0; i < 20; i++) {
                final  int tempI=i;
                executorService3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t受理业务"+"\t客户号"+tempI);
                });
            }
        }finally{
            executorService.shutdown();
        }




    }
}
