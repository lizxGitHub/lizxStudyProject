package com.lizx;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/9/25
 */
public class TestStringStringBufferStringBuilder {

    /**
     *
     *  速度快慢 StringBuilder>StringBuffer>String
     *  在拼接1万条数据时3者没有多大性能差异
     *  在拼接1千万数据时StringBuilder与StringBuffer无太大性能差异
     *  原因：String在每次拼接字符串时都会new一个StringBuilder对象
     *  StringBuffer就是在StringBuilder的每个方法上加synchronized关键字
     *  所以中心是StringBuilder
     *
     * 多个线程执行时StringBuilder要丢失数据
     * 主要原因是在append字段的时候，如果有两个线程同时操作，由于StringBuilder底层是通过计算添加count来赋值的 可能会出现同一个count赋予了多个值的情况
     *
     *
     */

    public static void main(String[] args) {

        String str1 = null;
        String str2 = "222222";
        String str = str1 + str2;

        System.out.println(str);


        /** 线程安全 **/
//        int times = 10000;
//
//        StringBuilder sbd = new StringBuilder();
//        for (int i = 0; i < times; i++) {
//            new Thread(()->{
//                sbd.append(1);
//                System.out.println(sbd.length());
//            }).start();
//        }

//        StringBuffer sbf = new StringBuffer();
//        for (int i = 0; i < times; i++) {
//            new Thread(new Runnable(){
//                @Override
//                public void run() {
//                    sbf.append(1);
//                    System.out.println(sbf.length());
//                }
//            }).start();
//        }


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("StringBuffer=========>>" + sbd.length());


        /** 线程安全 **/



        /** 三者性能 **/
//        int time = 100000000;
//        TestStringStringBufferStringBuilder sss = new TestStringStringBufferStringBuilder();
//        sss.sBuilder(time, "mN--");
//        sss.sBuffer(time, "mN--");
//        sss.sString(time, "mN--");
        /** 三者性能 **/

    }

    /** 线程安全 **/

    /** 线程安全 **/

    /** 三者性能 **/
    public void sString (int times, String str) {
        long start = System.currentTimeMillis();
        String put = "";
        for (int i = 0; i < times; i++) {
            put += str;
        }
        long end = System.currentTimeMillis();
        long usedTime = (end - start) / 1000;
//        System.out.println(put);
        System.out.println("String==============>" + usedTime);
    }

    public void sBuffer (int times, String str) {
        long start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sbf.append(str);
        }
        long end = System.currentTimeMillis();
        long usedTime = (end - start) / 1000;
//        System.out.println(sbf.toString());
        System.out.println("StringBuffer==============>" + usedTime);
    }

    public void sBuilder (int times, String str) {
        long start = System.currentTimeMillis();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sbd.append(str);
        }
        long end = System.currentTimeMillis();
        long usedTime = (end - start) / 1000;
//        System.out.println(sbd.toString());
        System.out.println("StringBuilder==============>" + usedTime);
    }

    /** 三者性能 **/
}
