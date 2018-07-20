package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполни карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуй логику записи в файл и чтения из файла для карты properties.

Требования:
1. Метод fillInPropertiesMap должен считывать данные с консоли.
2. Метод fillInPropertiesMap должен создавать FileInputStream, передавая считанную строку в качестве параметра.
3. Метод fillInPropertiesMap должен вызывать метод load передавая только что созданный FileInputStream в качестве параметра.
4. Метод save должен сохранять карту properties в полученный в качестве параметра объект типа OutputStream.
5. Метод load должен восстанавливать состояние карты properties из полученного в качестве параметра объекта типа InputStream.
*/

public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties prop = new Properties();



    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file = read.readLine();
        read.close();
        FileInputStream in = new FileInputStream(file);
        load(in);
        in.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        for (Map.Entry<String, String> map : properties.entrySet()){
            prop.setProperty(map.getKey(), map.getValue());
        }
        prop.store(outputStream, "");

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.load(inputStream);
        Set<String> keys = prop.stringPropertyNames();
        for (String s : keys){
            properties.put(s, prop.getProperty(s));
        }
    }

    public static void main(String[] args) throws Exception {
        /*Solution test = new Solution();
        test.fillInPropertiesMap();

        FileOutputStream in = new FileOutputStream("D:\\Java_Rush\\project\\file22.prop");
        test.save(in);
        in.close();*/

    }
}
