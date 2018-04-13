package com.javarush.task.task19.task1920;

/* 
Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:

имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Вывести в консоль имена в алфавитном порядке, у которых максимальная сумма.
Имена разделять пробелом либо выводить с новой строки.
Закрыть потоки.
Пример входного файла:

Петров 0.501
Иванов 1.35
Петров 0.85
Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        BufferedReader in = new BufferedReader(new FileReader(file));
        TreeMap<String, Double> map = new TreeMap<>();
        while (in.ready()){
            String[] s = in.readLine().split(" ");
            Double value = Double.valueOf(s[1]);
            if(map.containsKey(s[0])){
                value = value + map.get(s[0]);
            }
            map.put(s[0], value);
        }
        in.close();
        double max = 0.00;
        for(Map.Entry<String, Double> m : map.entrySet()){
            if(max < m.getValue()) {
                max = m.getValue();
            }
        }
        TreeSet<String> names = new TreeSet<>();
        for(Map.Entry<String, Double> m : map.entrySet()){
            if(max == m.getValue()) {
                names.add(m.getKey());
            }
        }
        for (String s : names){
            System.out.println(s);
        }
    }
}
