package com.example.myapplication;

public class JniTest {
    static {
        System.loadLibrary("Test");
    }
    public native int add(int a,int b);
}
