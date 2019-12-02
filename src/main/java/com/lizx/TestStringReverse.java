package com.lizx;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/10/16
 */
public class TestStringReverse {

    public static void main(String[] args) {
        /**
         *
         * 1.字符串翻转 StringBuilder与StringBuffer都有reverse方法
         * reverse方法实质a.检查编码相关b.将数组角标进行替换
         * 自定义替换方法思路：str.toCharArray() 遍历将首位重排
         *
         * 2.equals代码 本质是== 只是在String中equals方法被重写了 作为值比较 主要是通过char做字符串比较
         *
         */

        Integer i1 = 1;
        Integer i2 = 2;
        System.out.println(i1.equals(i2));


        String str = "123456789";
        StringBuilder sbd = new StringBuilder().append(str);
        sbd.reverse();
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        System.out.println(reverse);

    }
}
