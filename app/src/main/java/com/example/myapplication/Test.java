package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressLint("NewApi")
public class Test {
    public static void main(String []args){
        Map<Integer, String> map=new ConcurrentHashMap<>();

        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");
        map.put(5,"five");
        map.put(6,"six");
//        test(map);


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






}
