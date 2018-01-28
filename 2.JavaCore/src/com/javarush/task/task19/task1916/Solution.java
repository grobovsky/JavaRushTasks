package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Отслеживаем изменения

Считать с консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
В оригинальном и редактируемом файлах пустых строк нет.

Пример:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines) 

строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
строка4                            REMOVED строка4
строка5         строка5            SAME строка5
                строка0            ADDED строка0
строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
                строка5            ADDED строка5
строка4         строка4            SAME строка4
строка5                            REMOVED строка5
*/


//По этой ссылке разъяснение смысла задачи и даже с ним пришлось повозиться.
//http://info.javarush.ru/JavaRush_tasks_discussion/2016/02/14/level19-lesson10-bonus01-%D0%93%D1%80%D0%B0%D1%84%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B5-%D0%BF%D0%BE%D1%8F%D1%81%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5-%D1%83%D1%81%D0%BB%D0%BE%D0%B2%D0%B8%D1%8F-%D0%B7%D0%B0%D0%B4%D0%B0%D1%87%D0%B8-.html

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file1 = read.readLine();
        String file2 = read.readLine();
        read.close();
        ArrayList<String> lines1 = new ArrayList<>();
        ArrayList<String> lines2 = new ArrayList<>();
        FileReader fileReader1 = new FileReader(file1);
        BufferedReader in1 = new BufferedReader(fileReader1);
        while (in1.ready()){
            lines1.add(in1.readLine());
        }
        FileReader fileReader2 = new FileReader(file2);
        BufferedReader in2 = new BufferedReader(fileReader2);
        while (in2.ready()){
            lines2.add(in2.readLine());
        }
        in1.close();
        in2.close();
        String a = null;
        String b = null;
        for(int i = 0; i < lines1.size(); i++){
            if(!lines2.isEmpty()) {
                if (lines2.size() > 1) {
                    a = lines2.get(0);
                    b = lines2.get(1);
                } else if (lines2.size() == 1) {
                    a = lines2.get(0);
                }
                if (lines1.get(i).equals(a)) {
                    lines.add(new LineItem(Type.SAME, lines1.get(i)));
                    lines2.remove(0);
                } else if (lines1.get(i).equals(b)) {
                    lines.add(new LineItem(Type.ADDED, a));
                    lines2.remove(0);
                    i--;
                } else {
                    lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
                }
            }else {
                lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
            }
        }
        if(!lines2.isEmpty()){
            for(String s : lines2) lines.add(new LineItem(Type.ADDED, s));
        }
        //for(LineItem i : lines) System.out.println(i.type + " " + i.line);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
