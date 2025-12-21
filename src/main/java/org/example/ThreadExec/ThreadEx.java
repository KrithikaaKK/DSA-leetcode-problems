package org.example.ThreadExec;

import java.net.Inet4Address;
import java.util.concurrent.*;

class Book extends Thread {
    public void run(){
        for(int i=0;i<6;i++) {
            System.out.println(i+"(1)");
        }
        System.out.println(Thread.currentThread().getState());
        Num num = new Num();
        num.start();
        if(num.isAlive()) System.out.println("State of num thread: " + num.getState());
        num.setPriority(Thread.MAX_PRIORITY);
        System.out.println(num.getPriority());
        System.out.println("State of thread 2 after executing: "+ num.getState());
    }
}
class Num extends Thread{
    public void run(){
        for(int i=0;i<8;i++){
            System.out.println(i+"(2)");
        }
    }
}

class Customer implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        Thread.currentThread().setPriority(1);
        return Thread.currentThread().getPriority();
    }
}

public class ThreadEx {



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Book book = new Book();
        Num num = new Num();

        ExecutorService executorService = Executors.newFixedThreadPool(10);




           Future<Integer> future =  executorService.submit(new Customer());
           int v =future.get();
        System.out.println("nnn.."+v);
        //}




//        System.out.println("state of  thread 1 before executing: " + book.getState());
//        book.start();
//        System.out.println("state of thread 1 during executing: " + book.getState());
//        book.join();
//        book.setPriority(Thread.MIN_PRIORITY);
//        System.out.println("state of thread 1 after execution: " + book.getState());
//        Thread.sleep(1500);
//        System.out.println("Name:" +Thread.currentThread().getName()+"  state  "+book.getState());
//        book.setDaemon(true);
//        if(book.isDaemon()) System.out.println("thread 1 is declared as a daemon thread");
//        book.setDaemon(false);
//        if(book.isDaemon()) System.out.println(" thread 1 is a daemon thread");
//
//        System.out.println("main");

    }
}

