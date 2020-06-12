package com.example.myapplication;

import android.util.Log;

/**
 * @author: caoxinghua3
 * @date: 2020/6/4
 */
public class Task {
    public Task next = null;
    public int id;

    public Task(int id) {
        this.id = id;
    }


    public void run() {

        System.out.println("task id:" + id);
//        if (next != null) {
//            next.run();
//        }else {
//            System.out.println("task id:"+id+" is over");
//        }
    }

    interface ITaskCallBack {
        void complete();
    }
}
