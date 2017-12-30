package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> alphabet = new ArrayList<>();
        for(int i = 0; i < 58; i++){
            if(i < 26 || i >= 32) {
                alphabet.add(i + 65);
            }
        }
        FileInputStream stream = new FileInputStream(args[0]);
        int count = 0;
        while(stream.available() > 0){
            if(alphabet.contains(stream.read())){
                count++;
            }
        }
        System.out.println(count);
    }
}
