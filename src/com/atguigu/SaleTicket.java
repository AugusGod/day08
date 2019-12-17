package com.atguigu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tickets {
     int number=30;
   private Lock lock =new ReentrantLock();
   public void sale(){
       lock.lock();
       try {
           if(number>0){
               System.out.println(Thread.currentThread().getName()+"\t卖出第几张票"+(number--)+"\t还剩下"+number);
           }
       }finally {
           lock.unlock();
       }
   }
}




class SaleTicket {
    public  static  void main(String[] args){
        Tickets tickets = new Tickets();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
       try{
           for (int i = 0; i < 30; i++) {
               executorService.execute(()->{
                   tickets.sale();
               });
           }
       }finally {
           executorService.shutdown();
       }
    }
}
