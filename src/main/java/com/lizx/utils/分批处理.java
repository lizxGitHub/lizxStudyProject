package com.lizx.utils;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/9/27
 */
public class 分批处理 {

    public static void main(String[] args) {
        int dataNum = 36; // 数据量
        int loopTime = 10; // 批处理数量
        int dataNumTemp = 36; // 临时数据

        int loopOut = dataNum % loopTime > 0 ? dataNum / loopTime + 1 : dataNum / loopTime; // 外部循环次数，如果有余数就多循环一次
        for (int i = 0; i < loopOut; i++) {
            int loopIn = dataNumTemp > loopTime ? loopTime : dataNumTemp;// 内部循环次数，如果临时数据大于批处理数量 循环批处理数据量 否则循环 剩下的 临时数据量
            for (int j = 0; j < loopIn; j++) {
                System.out.println(i * loopTime + j);
            }
            dataNumTemp -= loopTime; // 循环一次 少一次批处理数据量
        }
    }

}
