package com.atguigu;

import javax.management.StringValueExp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
public class MyCache {
    private volatile Map<String,String> map=new HashMap<>();
    //读写分离锁
    ReentrantReadWriteLock rwk=new ReentrantReadWriteLock();
//实现读写分离
    public void put(String key,String value){
      rwk.writeLock().lock();
      try{
        System.out.println(Thread.currentThread().getName()+"\t写分离开始");
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"\t写分离结束");
      }finally{
          rwk.writeLock().unlock();
      }
    }



    public void get (String key){
         rwk.readLock().lock();
         try{
             System.out.println(Thread.currentThread().getName()+"\t读取开始");
             map.get(key);
             System.out.println(Thread.currentThread().getName()+"\t读取分离结束");
         }finally {
             rwk.readLock().unlock();
         }

    }



    /*private Lock lock=new ReentrantLock();
    //写方法
    public void put(String key, String value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        }finally{
            lock.unlock();
        }
    }

    //读方法
    public void get(String key){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()
            +"\t读取开始");
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取结束");
        }finally{
            lock.unlock();
        }
    }*/


}
//主线程
class  mian{
  public static void main(String [] args){
     //操作资源类
      MyCache myCache=new MyCache();
      //写入十次
      for (int i = 1; i < 30; i++) {
         final int temp=i;
          new Thread(()->{
            myCache.put(temp+"",temp+"");
          },String.valueOf(i)).start();
      }

     //读取十次
      for (int i = 1; i <30 ; i++) {
          final  int temp=i;
          new Thread(()->{
              myCache.get(temp+"");
          },String.valueOf(i)).start();
      }
  }
}
