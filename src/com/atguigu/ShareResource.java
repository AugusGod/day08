package com.atguigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
 class ShareResource {
     //编写资源类
      private  int flag =1;//A B C
      private Lock lock=new ReentrantLock();
      private  Condition c1=lock.newCondition();
      private  Condition c2=lock.newCondition();
      private Condition c3 =lock.newCondition();
    //编写方法
    public void print5() throws InterruptedException {
        lock.lock();
        try{
            //判断1    干活1   通知2
            while(flag!=1){
             c1.await();
            }
            for (int i = 0; i <5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print10() throws InterruptedException {
        lock.lock();
        try{
            //判断   干活  通知
            while(flag!=2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=3;
            c3.signal();
        }finally{
            lock.unlock();
        }

    }
    public void print15() throws InterruptedException {
        lock.lock();
        try{
            //判断   干活  通知
            while(flag!=3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=1;
            c1.signal();
        }finally{
            lock.unlock();
        }

    }

}
//主线程
class ThreadOrderAccess
{
    public  static  void main(String [] args){
        //操作资源类的对象
        ShareResource sh=new ShareResource();
/*
                AA打印5次，BB打印10次，CC打印15次
                * 接着
                * AA打印5次，BB打印10次，CC打印15次
                * ......来10轮
*/
        new Thread (()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread (()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread (()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();





    }
}
