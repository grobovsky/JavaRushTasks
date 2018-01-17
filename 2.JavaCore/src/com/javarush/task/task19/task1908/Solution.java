package com.javarush.task.task19.task1908;

/* 
Выделяем числа

Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file1 = read.readLine();
        String file2 = read.readLine();
        read.close();
        BufferedReader in = new BufferedReader(new FileReader(file1));
        BufferedWriter out = new BufferedWriter(new FileWriter(file2));
        while(in.ready()){
            String str = in.readLine();
            String[] words = str.split(" ");
            for(String s : words){
                if(s.matches("\\d+")){
                    out.write(s +" ");
                }
            }
        }
        in.close();
        out.close();
    }
}
