package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException{
        //создаю мапу с кодами ascii
        TreeMap<Character, Integer> map = new TreeMap<>();
        for(int i = 0; i < 128; i++){
            map.put((char)i, 0);
        }
        //читаю файл и считаю повторения
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(args[0]));
        while (in.available() > 0){
            char ch = (char)in.read();
            for(Map.Entry<Character, Integer> item : map.entrySet()){
                if(ch == item.getKey()){
                    item.setValue(item.getValue()+1);
                }
            }
        }
        in.close();
        //вывожу результат
        for(Map.Entry<Character, Integer> res : map.entrySet()){
            if(res.getValue() != 0){
                System.out.println(res.getKey()+ " " + res.getValue());
            }
        }
    }
}
