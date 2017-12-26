package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        Scanner scan = new Scanner(System.in);
        FileInputStream stream;
        while (true){
            String file = scan.nextLine();
            stream = new FileInputStream(file);
            if(stream.available() < 1000){
                stream.close();
                throw new DownloadException();
            }

        }
    }

    public static class DownloadException extends Exception {

    }
}
