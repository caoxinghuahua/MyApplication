package com.example.myapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: caoxinghua3
 * @date: 2020/6/4
 */
public class AsyncTest {

    public static void main(String[] args) {
        Object obj=new Object();
       new Thread(){
           @Override
           public void run() {
               super.run();
               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (obj){
                   obj.notifyAll();
               }
               System.out.println("obj  111");
           }
       }.start();
       synchronized (obj){
           try {
               obj.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       System.out.println("obj  222");
    }

}
