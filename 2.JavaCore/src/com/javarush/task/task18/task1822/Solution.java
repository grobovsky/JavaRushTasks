package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        
        BufferedReader in = new BufferedReader(new FileReader(file));
        while (in.ready()){
            String s = in.readLine();
            if(s.startsWith(args[0])){
                System.out.println(s);
                break;
            }
        }
        in.close();
    }
}
