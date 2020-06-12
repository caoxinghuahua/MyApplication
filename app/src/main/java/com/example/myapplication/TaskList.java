package com.example.myapplication;

/**
 * @author: caoxinghua3
 * @date: 2020/6/4
 */
public class TaskList {
    private Task head;
    private Task next;


    public void addTask(Task task) {
        if (task == null) return;
        if (head == null && next == null) {
            head = next = task;
        }else {
            next.next=task;
            next=task;
        }

    }

    public void start() {
        while (head != null) {

            head.run();
            head = head.next;
        }
        if (iTaskCallBack != null) {
            iTaskCallBack.complete();
        }
    }

    public void setTaskCallBack(Task.ITaskCallBack iTaskCallBack) {
        this.iTaskCallBack = iTaskCallBack;
    }

    private Task.ITaskCallBack iTaskCallBack;
}
