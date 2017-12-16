package com.javarush.task.task18.task1802;

import java.io.FileInputStream;
import java.util.Scanner;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        FileInputStream stream = new FileInputStream(file);
        int min = 0;
        if(stream.available() > 0) min = stream.read();
        while(stream.available() > 0){
            int value = stream.read();
            if(min > value){
                min = value;
            }
        }
        stream.close();
        System.out.println(min);
    }
}
