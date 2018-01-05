package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        String key = "squid";
        FileInputStream in = new FileInputStream(args[1]);
        FileOutputStream out = new FileOutputStream(args[2]);
        byte[] buff = new byte[in.available()];
        in.read(buff, 0, in.available());
        if(args[0].equals("-e")){
            out.write(encrypt(buff, key));
        }
        if(args[0].equals("-d")){
            out.write(decrypt(buff, key));
        }
        in.close();
        out.close();
    }
    public static byte[] encrypt(byte[] inc, String key){
        byte[] keyBytes = key.getBytes();
        byte[] result = new byte[inc.length];
        for (int i = 0; i < inc.length; i++) {
            result[i] = (byte)(inc[i] ^ keyBytes[i % keyBytes.length]);
        }
        return result;
    }
    public static byte[] decrypt(byte[] inc, String key){
        byte[] keyBytes = key.getBytes();
        byte[] result = new byte[inc.length];
        for (int i = 0; i < inc.length; i++) {
            result[i] = (byte) (inc[i] ^ keyBytes[i % keyBytes.length]);
        }
        return result;
    }

}
