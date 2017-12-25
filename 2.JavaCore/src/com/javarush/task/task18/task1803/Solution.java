package com.javarush.task.task18.task1803;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        FileInputStream stream = new FileInputStream(file);
        ArrayList<Integer> bytes = new ArrayList<Integer>();
        while(stream.available() > 0){
            bytes.add(stream.read());
        }
        stream.close();

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < bytes.size(); i++){
            if(map.containsKey(bytes.get(i))){
                map.put(bytes.get(i), map.get(bytes.get(i))+1);
            }else
                map.put(bytes.get(i), 1);
        }
        //System.out.println(map);
        int tmpValue = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > tmpValue){
                tmpValue = entry.getValue();
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == tmpValue){
                System.out.print(entry.getKey()+ " ");
            }
        }
        //System.out.println(tmpValue);
    }
}
