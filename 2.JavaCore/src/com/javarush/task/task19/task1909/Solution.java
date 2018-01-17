package com.javarush.task.task19.task1909;

/* 
Замена знаков

Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла и заменить все точки "." на знак "!".
Результат вывести во второй файл.
Закрыть потоки.
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
        while (in.ready()){
            String str = in.readLine();
            if(str.contains(".")){
                String s = str.replace('.', '!');
                out.write(s);
            }
        }
        in.close();
        out.close();
    }
}
