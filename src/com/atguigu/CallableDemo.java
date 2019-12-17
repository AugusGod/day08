package com.atguigu;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Mythead implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println("============come in call");
        return 1024;
    }
}



//第三种获得线程的方式
public class CallableDemo {
    public static  void main(String[]  args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=new FutureTask(new Mythead());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());

    }
}
