package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String file = read.readLine();
            if(file.equals("exit")){
                break;
            }
            ReadThread rt = new ReadThread(file);
            rt.start();

        }
        read.close();
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;
            //implement constructor body
        }

        @Override
        public void run() {
            HashMap<Integer, Integer> bytes = new HashMap<>();
            try {
                FileInputStream in = new FileInputStream(fileName);
                while(in.available() > 0) {
                        int key = in.read();
                        if (bytes.containsKey(key)) {
                            bytes.put(key, bytes.get(key) + 1);
                        } else
                            bytes.put(key, 1);
                    }
                in.close();
                int max = 0;
                int findByte = 0;
                for(Map.Entry<Integer, Integer> map : bytes.entrySet()){
                    if(max < map.getValue()){
                        max = map.getValue();
                        findByte = map.getKey();
                    }
                }
                synchronized (resultMap){
                    resultMap.put(fileName, findByte);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
