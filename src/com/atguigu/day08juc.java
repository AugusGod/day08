package com.atguigu;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
*线程    操作   资源
* */

class Ticket{
    //高内聚     实现封装资源和方法
    private  int tickets=30000;

   // new Thread().start();


    //运用重复可用的锁ReentrantLock()
    private Lock lock=new ReentrantLock();


    public void saleTickets() {
    //实现售票的业务逻辑
        //    //如果票数仍然大与0 择实行输出操作通过线程获得线程的名称
        lock.lock();//枷锁
    try {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "\t卖出第："
                    + (tickets--) + "\t还剩" + tickets);
        }
    } finally {
        lock.unlock();//解锁
    }


//        if(tickets>0){
//            System.out.println(Thread.currentThread().getName()+"\t卖出第："
//            +(tickets--)+"\t还剩下："+tickets);
//        }
}
}

public class day08juc {
    //java 主程序的入口
    public static void main(String[] args) {
//       /主要有三个人进行抢票的操作
//      Thread t1=new Thread();
//      Thread t2=new Thread();
//      Thread t3=new Thread();
        Ticket ticket=new Ticket();
       // /创建线程池 的三个线程
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ///ThreadPoolExecutor.AbortPolicy();

        try {
            for (int i = 0; i < 30000; i++) {
                executorService.execute(()->{ticket.saleTickets();});
            }
        } finally {

            executorService.shutdown();
        }
    }
}

        /*Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 350; i++) {
                    ticket.saleTickets();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 350; i++) {
                    ticket.saleTickets();

                }

            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 350; i++) {
                    ticket.saleTickets();

                }

            }
        },
                "c").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 350; i++) {
                    ticket.saleTickets();
                }
            }
        }, "D").start();


    }*/











