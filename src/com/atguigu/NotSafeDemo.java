package com.atguigu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NotSafeDemo {
    public static void main(String [] args){
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(
                    ()->{
                      map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
                      System.out.println(map);
                    },String.valueOf(i)
            ).start();
        }




    }

}
