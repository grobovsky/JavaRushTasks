package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        int count = 0;
        while(fileReader.ready()){
            String str = fileReader.readLine();
            String[] words = str.split("\\W");
            for(String s : words) {
                if (s.equals("world")) {
                    count++;
                }
            }
        }
        fileReader.close();
        System.out.println(count);
        
        
        // такой способ не проходит валидатор, хотя результат выдает правильный, не разобрался в чем тут проблема
        /*StringBuilder build = new StringBuilder();
        while (fileReader.ready()){
            build.append(fileReader.readLine());
        }
        fileReader.close();
        String res = new String(build);
        Pattern p = Pattern.compile("\\bworld\\b");
        Matcher m = p.matcher(res);
        int count = 0;
        while (m.find()){
            count++;
            //System.out.println(m.group());
        }*/
    }
}
