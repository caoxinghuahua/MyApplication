package com.example.myapplication.async;

/**
 * @author: caoxinghua3
 * @date: 2020/6/4
 */
public class Demo1 extends BaseDemo {
    private final Object lock = new Object();

    @Override
    public void callback(long response) {
        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");

        synchronized (lock) {
            lock.notifyAll();
        }

    }

    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();

        demo1.call();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (demo1.lock) {
                    System.out.println("xxxxxx222");

                }
            }
        }.start();
        synchronized (demo1.lock) {
            System.out.println("xxxxxx111");

            try {
                demo1.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("主线程内容");

    }

}
