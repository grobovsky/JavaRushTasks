package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<FileInputStream> in = new ArrayList<>();
        while (true){
            String fileName = read.readLine();
            try{
                in.add(new FileInputStream(fileName));
            }catch (FileNotFoundException e){
                for(FileInputStream i : in) i.close();
                read.close();
                System.out.println(fileName);
                return;
            }
        }
    }
}
