package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String f1 = scan.nextLine();
        String f2 = scan.nextLine();
        scan.close();
        BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(f1));
        byte[] buff = new byte[in1.available()];
        in1.read(buff, 0, buff.length);
        boolean append = false;
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f1, append));
        BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(f2));
        byte[] buff2 = new byte[in2.available()];
        in2.read(buff2, 0, buff2.length);
        out.write(buff2, 0, buff2.length);
        append = true;
        out.write(buff, 0, buff.length);

        in1.close();
        in2.close();
        out.close();
    }
}
