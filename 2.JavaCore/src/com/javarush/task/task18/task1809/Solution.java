package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String file1 = scan.nextLine();
        String file2 = scan.nextLine();
        FileInputStream in = new FileInputStream(file1);
        FileOutputStream out = new FileOutputStream(file2);
        byte[] buff = new byte[in.available()];
        int len = in.read(buff);
        for(int i = 0; i < buff.length-1; i++){
            for(int j = i+1; j < buff.length; j++){
                byte tmp = buff[i];
                buff[i] = buff[j];
                buff[j] = tmp;
            }
        }
        out.write(buff, 0, len);
        in.close();
        out.close();
        //for(byte i : buff) System.out.print(i+" ");


    }
}
