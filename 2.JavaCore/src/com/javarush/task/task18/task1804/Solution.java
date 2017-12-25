package com.javarush.task.task18.task1804;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        FileInputStream stream = new FileInputStream(file);
        HashMap<Integer, Integer> map = new HashMap<>();
        while(stream.available() > 0){
            int bytes = stream.read();
            if(map.containsKey(bytes)){
                map.put(bytes, map.get(bytes)+1);
            }else
                map.put(bytes, 1);
        }
        stream.close();
        int minValues = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(minValues > entry.getValue()){
                minValues = entry.getValue();
            }
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(minValues == entry.getValue()){
                System.out.print(entry.getKey()+" ");
            }
        }
        //System.out.println(map);
    }
}
