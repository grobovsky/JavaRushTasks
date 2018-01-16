package com.javarush.task.task19.task1906;

/* 
Четные символы

Считать с консоли 2 имени файла.
Вывести во второй файл все символы с четным индексом.

Пример первого файла:
text in file
Вывод во втором файле:
eti ie

Закрыть потоки ввода-вывод
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file1 = read.readLine();
        String file2 = read.readLine();
        read.close();
        FileReader in = new FileReader(file1);
        FileWriter out = new FileWriter(file2);
        int count = 1;
        while (in.ready()){
            int tmp = in.read();
            if(count%2 == 0){
                out.write(tmp);
            }
            count++;
        }
        in.close();
        out.close();
    }
}
