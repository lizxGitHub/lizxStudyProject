package com.lizx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author com.mhout.lizx
 * @version 1.0.0
 * @ClassName:
 * @Description:
 * @date 2019/10/9
 */
public class 编译更新工具 {

    // 项目地址 target下项目的地址
    private static String projectDir = "D:\\workSpace\\mht_bdmp\\target\\mht_bdmp"; // 改动
    // 编译前文件地址
    private static String compileDirB = "D:\\compileLC\\.java";
    // 编译后文件地址
    private static String compileDirA = "D:\\compileLC\\.class";
    // 短地址.class
    private static String shortOneC = "\\WEB-INF\\classes";
    // 短地址.java
    private static String shortOneJ = "\\src\\main\\java";
    // 短地址资源文件地址
    private static String shortOneR = "\\src\\main\\resources";
    // 短地址资源文件地址
    private static String webappB = "\\WebContent"; // 改动
    private static String webappA = "";


    public static void main(String[] args) {
        /**
         * 1.获取需要编译的文件及文件地址
         * 2.拿到地址后去指定项目找编译后对应的文件
         * 3.将找到的文件复制到指定的路径下
         */

        编译更新工具 ff = new 编译更新工具();
        // 复制java
        Map<String, String> paraMapJ = new HashMap<>();
        ArrayList<File> filesJ = ff.getListFiles(compileDirB + shortOneJ);
        for (int i = 0; i < filesJ.size(); i++) {
            int s = (compileDirB + shortOneJ).length();
            int m = filesJ.get(i).getAbsolutePath().lastIndexOf(filesJ.get(i).getName());
            //需要复制的目标文件或目标文件夹
            String pathname = projectDir + shortOneC + filesJ.get(i).getAbsolutePath().substring(s, m) + filesJ.get(i).getName().split("\\.")[0] + ".class";
            File file = new File(pathname);
            //复制到的位置
            String topathname = compileDirA + shortOneC + filesJ.get(i).getAbsolutePath().substring(s, m);
            File toFile = new File(topathname);
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            try {
                copy(file, toFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("java to class down!");

        // 复制resource
        Map<String, String> paraMapR = new HashMap<>();
        ArrayList<File> filesR = ff.getListFiles(compileDirB + shortOneR);
        for (int i = 0; i < filesR.size(); i++) {
            int s = (compileDirB + shortOneR).length();
            int m = filesR.get(i).getAbsolutePath().lastIndexOf(filesR.get(i).getName());
            //需要复制的目标文件或目标文件夹
            String pathname = projectDir + shortOneC + filesR.get(i).getAbsolutePath().substring(s, m) + filesR.get(i).getName();
            File file = new File(pathname);
            //复制到的位置
            String topathname = compileDirA + shortOneC + filesR.get(i).getAbsolutePath().substring(s, m);
            File toFile = new File(topathname);
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            try {
                copy(file, toFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("复制resource down!");

        // 复制webapp
        ArrayList<File> filesW = ff.getListFiles(compileDirB + webappB);
        for (int i = 0; i < filesW.size(); i++) {
            int s = (compileDirB + webappB).length();
            int m = filesW.get(i).getAbsolutePath().lastIndexOf(filesW.get(i).getName());
            //需要复制的目标文件或目标文件夹
            String pathname = compileDirB + webappB + filesW.get(i).getAbsolutePath().substring(s, m) + filesW.get(i).getName();
            File file = new File(pathname);
            //复制到的位置
            String topathname = compileDirA + filesW.get(i).getAbsolutePath().substring(s, m);
            File toFile = new File(topathname);
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            try {
                copy(file, toFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("复制webapp down!");
    }

    // 获取文件路径
    public static ArrayList<File> getListFiles(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        ArrayList<File> files = new ArrayList<File>();
        if (directory.isFile()) {
            files.add(directory);
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File fileOne = fileArr[i];
                files.addAll(getListFiles(fileOne));
            }
        }
        return files;
    }

    // 复制
    public static void copy(File file, File toFile) throws Exception {
        byte[] b = new byte[1024];
        int a;
        FileInputStream fis;
        FileOutputStream fos;
        if (file.isDirectory()) {
            String filepath = file.getAbsolutePath();
            filepath = filepath.replaceAll("\\\\", "/");
            String toFilepath = toFile.getAbsolutePath();
            toFilepath = toFilepath.replaceAll("\\\\", "/");
            int lastIndexOf = filepath.lastIndexOf("/");
            toFilepath = toFilepath + filepath.substring(lastIndexOf, filepath.length());
            File copy = new File(toFilepath);
            //复制文件夹
            if (!copy.exists()) {
                copy.mkdir();
            }
            //遍历文件夹
            for (File f : file.listFiles()) {
                copy(f, copy);
            }
        } else {
            if (toFile.isDirectory()) {
                String filepath = file.getAbsolutePath();
                filepath = filepath.replaceAll("\\\\", "/");
                String toFilepath = toFile.getAbsolutePath();
                toFilepath = toFilepath.replaceAll("\\\\", "/");
                int lastIndexOf = filepath.lastIndexOf("/");
                toFilepath = toFilepath + filepath.substring(lastIndexOf, filepath.length());

                //写文件
                File newFile = new File(toFilepath);
                fis = new FileInputStream(file);
                fos = new FileOutputStream(newFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            } else {
                //写文件
                fis = new FileInputStream(file);
                fos = new FileOutputStream(toFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            }
        }
    }
}

