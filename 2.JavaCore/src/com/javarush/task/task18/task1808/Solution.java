package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String file1 = scan.nextLine();
        String file2 = scan.nextLine();
        String file3 = scan.nextLine();
        FileInputStream stream1 = new FileInputStream(file1);
        FileOutputStream stream2 = new FileOutputStream(file2);
        FileOutputStream stream3 = new FileOutputStream(file3);
        byte[] outf1 = new byte[stream1.available()];
        int len = stream1.read(outf1);

        stream2.write(outf1, 0, len-len/2);
        //for(int i : outf1) System.out.print(i+" ");
        stream3.write(outf1, len-len/2, len/2);

        stream1.close();
        stream2.close();
        stream3.close();


    }
}
