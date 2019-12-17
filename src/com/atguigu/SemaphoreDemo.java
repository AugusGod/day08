package com.atguigu;

import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import org.omg.PortableInterceptor.Interceptor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args){
        //模拟三个车位)
        Semaphore semaphore=new Semaphore(3);
        //模拟六辆车
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                //初始值将flag定为false
                boolean flag=false;
               try{
                   semaphore.acquire();
                   flag=true;
                   //抢到车位
                   System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                   //暂停几秒钟线程
                   try{
                       TimeUnit.SECONDS.sleep(3);
                   }catch (InterruptedException e){  e.printStackTrace();}
                  //离开车位
                   System.out.println(Thread.currentThread().getName()+"\t 离开车位");
               } catch(InterruptedException e){
                   e.printStackTrace();
                }finally{
                   if(flag){
                       semaphore.release();
                   }
                }

            },String.valueOf(i)).start();
        }


    }



}
