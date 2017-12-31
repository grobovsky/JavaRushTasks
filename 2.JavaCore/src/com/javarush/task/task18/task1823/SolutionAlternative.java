package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

/*
Изначально решал эту задачу без HashMap через в поиск максимального байта в массиве. Из-за того, что невнимательно читал условие задачи
делал ошибку в результате, полез читать в нете инфу по решению и уткнулся в этот HashMap. Вдуплился, что косячу в результате, решил уже
c HashMap, но сохранил код первого решения. Интересно конечно еще получить оценку используемого подхода, кроме победы над валидатором..
*/

public class SolutionAlternative {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String file = read.readLine();
            if(file.equals("exit")){
                break;
            }
            new ReadThread(file).start();
        }
        read.close();
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        FileInputStream in;
        private String fileName;
        public ReadThread(String fileName) throws IOException{
            this.in = new FileInputStream(fileName);
            this.fileName = fileName;
            //implement constructor body
        }

        @Override
        public void run() {
            try {
                byte[] bytes = new byte[in.available()];
                while (in.available() > 0){
                    in.read(bytes, 0, bytes.length);
                }
                in.close();
                int count = 0;
                int maxRepeat = 0;
                int findedByte = 0;
                for(int i = 0; i < bytes.length; i++){
                    for(int j = 0; j < bytes.length; j++){
                        if(bytes[i] == bytes[j]){
                            count++;
                        }
                    }
                    if(maxRepeat < count){
                        maxRepeat = count;
                        findedByte = bytes[i];
                    }
                }
                synchronized (resultMap){
                    resultMap.put(fileName, findedByte);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
