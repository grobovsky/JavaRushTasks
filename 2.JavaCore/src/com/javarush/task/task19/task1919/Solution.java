package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени.
Закрыть потоки.
Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1
Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException{
        String file = "";
        if(args.length != 0){
            file = args[0];
        }
        FileReader in = new FileReader("D:\\Java_Rush\\project\\file11.txt");
        BufferedReader read = new BufferedReader(in);
        /*ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();*/
        TreeMap<String, Double> map = new TreeMap<>();
        //String[] str = read.readLine().split(" ");
        double d = 0.00;
        while (read.ready()){
            String[] s = read.readLine().split(" ");
            if(map.containsKey(s[0])){
                d = d * map.get(s[0]);
            }
            try{
                d = Double.valueOf(s[1]);
                map.put(s[0], d);
            }catch (NumberFormatException e){
                System.out.println("Wrong format");
            }

        }
        read.close();
        for(Map.Entry<String, Double> item : map.entrySet()){
            System.out.println(item.getKey() + " " + item.getValue());
        }


    }
}
