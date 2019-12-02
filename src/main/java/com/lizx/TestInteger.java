package com.lizx;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/10/24
 */
public class TestInteger {

    /**
     * 结果是true false
     * 原因：在封装类Integer中的高频区缓存是-127到128，在这之间的数会被缓存，再定义的话就直接在缓存中取 所以地址相等
     * Integer的equals方法是把Integer转为int在做比较
     *
     */
    public static void main(String[] args) {

        Integer a1 = 10;
        Integer a2 = 10;
        Integer a3 = 133;
        Integer a4 = 133;

//        System.out.println(a1 == a2);
//        System.out.println(a3 == a4);
//        System.out.println(a3.equals(a4));


        /**
         * 自动拆箱装箱与缓存的概念
         */
        int s1 = 1;
        Integer s2 = 1;
        Integer s22 = 1;
        Integer s3 = new Integer(1);
        Integer s4 = new Integer(1);
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
        System.out.println(s4 == s3);
        System.out.println(s4 == s2);
        System.out.println(s4 == s22);
        System.out.println(s4 == s1);
        System.out.println(s22 == s2);


    }

}
