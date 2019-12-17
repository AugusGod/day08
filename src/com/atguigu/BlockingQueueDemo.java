package com.atguigu;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws  Exception{
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a",3L, TimeUnit.SECONDS);
        blockingQueue.put("a");
        blockingQueue.put("s");
        System.out.println("***************11111111111");
       // blockingQueue.put("a");
        System.out.println("***************2222222222222");
        /*System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/
    }

}
