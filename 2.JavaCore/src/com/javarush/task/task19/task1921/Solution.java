package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String.
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами.
Заполнить список PEOPLE используя данные из файла.
Закрыть потоки.
Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        String file = args[0];
        BufferedReader in = new BufferedReader(new FileReader(file));
        while (in.ready()){

            String[] content = in.readLine().split("(?= [\\d])");
            String name = content[0].trim();
            try{
                int year = Integer.parseInt(content[content.length-1].trim());
                int month = Integer.parseInt(content[content.length-2].trim());
                int day = Integer.parseInt(content[content.length-3].trim());
                Date date = new GregorianCalendar(year, month-1, day).getTime();
                PEOPLE.add(new Person(name, date));
            }catch (NumberFormatException e){
                System.out.println("wrong format");
            }
        }
        in.close();
        /*for(int i = 0; i < PEOPLE.size(); i++){
            System.out.println(PEOPLE.get(i).getName() + " " + PEOPLE.get(i).getBirthday());
        }*/
    }
}
