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
        TreeMap<String, Double> map = new TreeMap<>();

        FileReader in = new FileReader(file);
        BufferedReader read = new BufferedReader(in);
        while(read.ready()){
            String[] array = read.readLine().split(" ");
            String name = array[0];
            Double selary = null;
            try{
                selary = Double.valueOf(array[1]);
            }catch (NumberFormatException e){
                System.out.println("wrong value");
            }
            if(map.containsKey(name)){
                selary = selary + map.get(name);
            }
            map.put(name, selary);
        }
        read.close();
        for(Map.Entry<String, Double> m : map.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }


    }
}
