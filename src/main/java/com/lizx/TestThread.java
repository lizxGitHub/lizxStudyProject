package com.lizx;

import java.util.Vector;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/9/25
 */
public class TestThread extends Thread {


//    private StringBuilder sb;
//    public TestThread(StringBuilder sb) {
//        this.sb = sb;
//    }

    private StringBuffer sb;
    public TestThread (StringBuffer sb) {
        this.sb = sb;
    }


    @Override
    public void run() {
//        this.sb.append(1);
//        System.out.println(this.sb.length());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int loop = 10;
        for (int i = 0; i < loop; i++) {
            sb.append(1);
        }

        System.out.println("sb.length in loop=====>" + sb.length());
//        System.out.println("线程名称=====>" + Thread.currentThread().getName());
    }

    public static void main(String[] arg) {
//        StringBuffer sb = new StringBuffer();
////        StringBuilder sb = new StringBuilder();
//        TestThread t1 = new TestThread(sb);
//        for (int i = 0; i < 10000; i++) {
//            new Thread(t1, "线程1").start();
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("====================>" + sb.length());

        int times = 10;
        StringBuffer sb = new StringBuffer();
        TestThread t1 = new TestThread(sb);

        /**
         * 所以判断线程是否结束不用isAlive 取而代之用join示例如下
         *  适用场景：在线程中的内容结束后要进行一些操作更新等
         */

        // 多个子线程
//        Vector<Thread> threadVector = new Vector<Thread>();
//        for (int i = 0; i < times; i++) {
//            Thread threadOne = new Thread(t1, "线程1");
//            threadVector.add(threadOne);
//            threadOne.start();
//        }
//        for (Thread thread : threadVector) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("都执行完了" + sb.length());

        // 一个线程
        Thread thread = new Thread(t1, "线程1");
        System.out.println("run 1");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程结束" + sb.length());
    }
}
