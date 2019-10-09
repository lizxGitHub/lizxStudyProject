package com.lizx;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName: 协程测试
 * @Description:
 * @date 2019/9/25
 */
public class TestQuasar {


    public static void main(String[] args) {


        int times = 5;


        //使用阻塞队列来获取结果。
        LinkedBlockingQueue<Fiber<Integer>> fiberQueue = new LinkedBlockingQueue<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {



        }




//        long start = System.currentTimeMillis();
//        for (int i = 0; i < times; i++) {
//            int finalI = i;
//            System.out.println(times + "times===========finalI" + finalI);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                        if (times == finalI + 1) {
//                            long end = System.currentTimeMillis();
//                            long usedTime = (end - start) / 1000;
//                            System.out.println("usedTime==============>" + usedTime);
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }


    }
}
