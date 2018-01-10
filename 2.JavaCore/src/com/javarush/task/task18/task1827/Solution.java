package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        scan.close();
        String currentId = currentId(fileName);
        addString(fileName, currentId, args[1], args[2], args[3]);



    }
    public static void addString (String fileName, String currentId, String productName, String price, String quantity){
        StringBuilder build = new StringBuilder(currentId);
        if(currentId.length() < 8){
            for(int i = currentId.length(); i < 8; i++){
                build.append(" ");
            }
        }
        if(currentId.length() >= 8){
            build.delete(8, build.capacity());
        }
        currentId = new String(build);

        build = new StringBuilder(productName);
        if(productName.length() < 30){
            for(int i = productName.length(); i < 30; i++ ){
                build.append(" ");
            }
        }
        if(productName.length() >= 30){
            build.delete(30, build.capacity());
        }
        productName = new String(build);

        build = new StringBuilder(price);
        if(price.length() < 8){
            for(int i = price.length(); i < 8; i++){
                build.append(" ");
            }
        }
        if(price.length() >= 8){
            build.delete(8, build.capacity());
        }
        price = new String(build);

        build = new StringBuilder(quantity);
        if(quantity.length() < 4){
            for(int i = quantity.length(); i < 4; i++){
                build.append(" ");
            }
        }
        if(quantity.length() >= 4){
            build.delete(4, build.capacity());
        }
        quantity = new String(build);

        build = new StringBuilder();
        String result =
                new String(build.append(currentId).append(productName).append(price).append(quantity).append("\r\n"));
        try(FileOutputStream out = new FileOutputStream(fileName, true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "windows-1251"))){
            writer.write(result, 0, result.length());
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String currentId (String fileName){
        ArrayList<String> strings = new ArrayList<>();
        try(FileInputStream in = new FileInputStream(fileName);
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "windows-1251"))) {
            while (read.ready()) {
                strings.add(read.readLine());
            }
            in.close();
            read.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        int maxId = 0;
        for(String s : strings){
            if(!s.equals("") && s != null) { 
                String tmp = s.substring(0, 8);
                int i = Integer.valueOf(tmp.trim());
                if (maxId < i) {
                    maxId = i;
                }
            }
        }
        return String.valueOf(maxId+1);
    }
}
