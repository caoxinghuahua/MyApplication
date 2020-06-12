package com.example.myapplication;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class Test {

    int a=1;

    public static void main(String []args){
        Map<Integer, String> map=new ConcurrentHashMap<>();
//        int s=a;
//        map.put(1,"one");
//        map.put(2,"two");
//        map.put(3,"three");
//        map.put(4,"four");
//        map.put(5,"five");
//        map.put(6,"six");
//        test(map);

        testLinkTask();
        Observable.interval(10, TimeUnit.SECONDS).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });


        Observable.create()
    }



    public static void test(Map<Integer, String> map){
        System.out.println("map = " + map);
        int size=map.size();
        for (int i = 1;i<=size;i++){
            map.remove(i);
            System.out.println("Already remove " + i);
            System.out.println("map " + map);
        }
        for (int i = map.size();i>0;i--){
//            map.remove(i);
//            System.out.print("Already remove " + i);
        }
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
//        while (it.hasNext()){
////            String s=map.get(it.next().getKey());
//            String s= map.remove(it.next().getKey());
//            System.out.println("value " +s);
//        }
        System.out.print("map = " + map);
    }

    public static void testLinkTask(){
        TaskList taskList=new TaskList();
        for(int i=0;i<10;i++){
            Task tmp=new Task(i+1);
            taskList.addTask(tmp);
        }
        taskList.setTaskCallBack(new Task.ITaskCallBack() {
            @Override
            public void complete() {
                System.out.println("task complete");
            }
        });
        taskList.start();
    }


}
