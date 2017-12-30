package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String f1 = scan.nextLine();
        String f2 = scan.nextLine();

        FileReader in = new FileReader(f1);
        StringBuilder sb = new StringBuilder();
        while(in.ready()){
            sb.append((char) in.read());
        }
        String s = new String(sb);
        String[] values = s.split(" ");
        sb = new StringBuilder();
        for(int i = 0; i < values.length; i ++){
            Integer tmp = Math.round(Float.parseFloat(values[i]));
            values[i] = String.valueOf(tmp);
            sb.append(values[i]+" ");
        }
        String sOut = new String(sb);
        FileWriter out = new FileWriter(f2);
        out.write(sOut);
        in.close();
        out.close();

        //for(String i : values)System.out.print(i+ " ");




    }
}
