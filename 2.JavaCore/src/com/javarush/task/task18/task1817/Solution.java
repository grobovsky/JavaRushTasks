package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        FileInputStream stream = new FileInputStream(args[0]);
        float allSimbols = stream.available();
        float spaceSimbols = 0;
        while (stream.available() > 0){
            if(stream.read() == 32){
                spaceSimbols++;
            }
        }
        stream.close();
        float res = spaceSimbols/allSimbols*100;
        System.out.println(String.format("%(.2f", res));
    }
}
