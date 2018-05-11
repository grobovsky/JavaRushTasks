package com.javarush.task.task19.task1925;

/* 
Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6.
В конце файла2 запятой не должно быть.
Закрыть потоки.

Пример выходных данных в файл2:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file1 = args[0];
        String file2 = args[1];

        BufferedReader in = new BufferedReader(new FileReader(file1));
        BufferedWriter out = new BufferedWriter(new FileWriter(file2));
        StringBuilder res = new StringBuilder();
        while (in.ready()) {
            String[] lines = in.readLine().split(" ");
            for(String s : lines){
                if(s.length() > 6){
                    res.append(s).append(",");
                }
            }
        }
        res.deleteCharAt(res.length()-1);
        out.write(res.toString());
        in.close();
        out.close();
    }
}
