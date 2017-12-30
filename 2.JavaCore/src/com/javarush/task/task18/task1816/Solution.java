package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> fileContent = new ArrayList<>();
        ArrayList<Integer> alphabet = new ArrayList<>();
        for(int i = 0; i < 58; i++){
            if(i < 26 || i >= 32) {
                alphabet.add(i + 65);
            }
        }
        //System.out.print(alphabet.toString());
        if(args[0] != null){
            FileInputStream stream = new FileInputStream(args[0]);
            while (stream.available() > 0){
                fileContent.add(stream.read());
            }
            stream.close();
        }
        int count = 0;
        for(int i = 0; i < fileContent.size(); i++){
            if(alphabet.contains(fileContent.get(i))){
                count++;
            }
        }
        System.out.println(count);
    }
}
