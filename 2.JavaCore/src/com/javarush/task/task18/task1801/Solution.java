package com.javarush.task.task18.task1801;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/*
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        
        InputStream stream = new FileInputStream(file);
        int max = 0;
        if(stream.available() > 0) max = stream.read();
        
        while(stream.available() > 0){
            int b = stream.read();
            if(max < b){
                max = b;
            }
        }
        stream.close();
        System.out.println(max);
     }
}
