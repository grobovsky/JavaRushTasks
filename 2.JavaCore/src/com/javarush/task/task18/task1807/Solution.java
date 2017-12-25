package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        FileInputStream stream = new FileInputStream(file);
        int[] buff = new int[stream.available()]; 
        int count = 0;
        for(int i= 0; stream.available() > 0; i++){
            buff[i] = stream.read();
            if(buff[i] == 44)count++;
        }
        stream.close();
        System.out.println(count);
        //for(int i : buff) System.out.println(i);
    }
}
