package com.javarush.task.task18.task1825;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> names = new TreeSet<>();
        String fullFileName = read.readLine();
        String fileName = fullFileName.substring(0, fullFileName.lastIndexOf(".part"));
        File file = new File(fileName);
        file.createNewFile();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, true));
        //System.out.println(path);
        while (!fullFileName.equals("end")){
            names.add(fullFileName);
            fullFileName = read.readLine();
        }
        read.close();
        BufferedInputStream in;
        for(String s : names){
            in = new BufferedInputStream(new FileInputStream(s));
            while (in.available() > 0){
                out.write(in.read());
            }
            in.close();
        }
        out.close();
    }
}
