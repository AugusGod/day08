package com.atguigu;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(7);
        for (int i = 0; i <7; i++) {
            final  int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()
                +"\t第"+temp+"个人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t最后同一个");

    }
}
