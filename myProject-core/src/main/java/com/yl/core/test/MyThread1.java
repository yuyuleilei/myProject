package com.yl.core.test;/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */


/**
 * @author Yulei
 * @version 1.0
 * @date 2017/2/20
 * @description synchronized的用法
 */
public class MyThread1 implements Runnable {
    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public static void main(String [] args){
        MyThread1 t1 = new MyThread1();
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t1, "B");
        ta.start();
        tb.start();
    }

}
