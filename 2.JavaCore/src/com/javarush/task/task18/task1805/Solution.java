package com.javarush.task.task18.task1805;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        FileInputStream stream = new FileInputStream(file);
        Set<Integer> sortBytes = new TreeSet<>();  //тут все само сортируется натуральным образом
        while(stream.available() > 0){
            sortBytes.add(stream.read());
        }
        stream.close();
        for(Integer b : sortBytes){
            System.out.print(b + " ");
        }
        //System.out.println(sortBytes.toString());
    }
}
