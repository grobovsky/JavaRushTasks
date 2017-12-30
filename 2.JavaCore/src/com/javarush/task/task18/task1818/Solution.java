package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String f1 = scan.nextLine();
        String f2 = scan.nextLine();
        String f3 = scan.nextLine();
        scan.close();
       
        BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(f2));
        BufferedInputStream in3 = new BufferedInputStream(new FileInputStream(f3));
        byte[] buff2 = new byte[in2.available()];
        byte[] buff3 = new byte[in3.available()];
        in2.read(buff2);
        in3.read(buff3);
        BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(f1));
        BufferedOutputStream out3 = new BufferedOutputStream(new FileOutputStream(f1, true));
        out2.write(buff2, 0, buff2.length);
        out3.write(buff3, 0, buff3.length);
        scan.close();
        in2.close();
        in3.close();
        out2.close();
        out3.close();

    }
}
