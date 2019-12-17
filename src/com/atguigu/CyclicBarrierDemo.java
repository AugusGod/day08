package com.atguigu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void  main(String[] args){
        //完成之前 必须要等
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("七个一个都不能少");
        });

        for (int i = 0; i < 7; i++) {
            final  int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t第"+temp+"\t颗");
            //完成之前都必须要等待
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }



}
