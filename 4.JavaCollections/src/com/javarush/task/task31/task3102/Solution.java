package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File folder = new File(root);
        List<String> list = new ArrayList<>();
        Deque<File> stack = new ArrayDeque<>();
        File[] files = folder.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                stack.push(f);
            } else {
                list.add(f.getAbsolutePath());
            }
        }
        while (!stack.isEmpty()) {
            File[] tmp = stack.pop().listFiles();
            for (File f : tmp) {
                if (f.isDirectory()) {
                    stack.push(f);
                } else {
                    list.add(f.getAbsolutePath());
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        
    }
}
