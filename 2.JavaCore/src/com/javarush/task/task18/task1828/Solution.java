package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

/*
Код переписывал 4 раза.. и парсил тупо строки и бил на разные типы, а потом клеил строку.. в итоге валидатор не пропускал
потому что в потоках чтения и записи указал кодировку. Убрал кодировку и валидатор пропустил.
*/


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        if(args.length == 0){return;}
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        scan.close();
        //String fileName = "C:\\Users\\Anna\\Documents\\JavaRushTasks\\file3.txt";
        List<String> list = new ArrayList<>();
        try(FileInputStream in = new FileInputStream(fileName);
            BufferedReader read = new BufferedReader(new InputStreamReader(in))){
            while (read.ready()){
                list.add(read.readLine());
            }
            read.close();
        }catch (IOException e){
            e.printStackTrace();
        }
            switch (args[0]) {
                case "-u":
                    if(args.length != 5){return;}
                    int argId = 0;
                    int qty;
                    float price;
                    try{
                        price = Float.parseFloat(args[args.length-2]);
                        qty = Integer.parseInt(args[args.length-1]);
                        argId = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e){
                        System.out.println("wrong argument");
                        return;
                    }
                    String productName;
                    if (args.length > 5) {
                        StringBuffer buf = new StringBuffer();
                        for (int i = 2; i < args.length - 2; i++)
                            buf.append(args[i]).append(" ");
                        productName = buf.substring(0, buf.length() - 1);
                    } else
                        productName = args[2];
                    //String id = args[1], productName = args[2], price = args[3], qty = args[4];
                    String currentStr = String.format("%-8d%-30s%-8.2f%-4d", argId, productName, price, qty);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) != null && !list.get(i).equals("")) {
                            int tmp = Integer.parseInt(list.get(i).substring(0, 8).trim());
                            if (tmp == argId) {
                                list.set(i, currentStr);
                            }
                        }
                    }
                    break;
                case "-d":
                    if(args.length != 2){return;}
                    argId = Integer.parseInt(args[1]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) != null && !list.get(i).equals("")) {
                            int tmp = Integer.parseInt(list.get(i).substring(0, 8).trim());
                            if (tmp == argId) {
                                list.remove(i);
                            }
                        }
                    }
                    break;
            }

            //for (String s : list) System.out.println(s);
            try (FileOutputStream out = new FileOutputStream(fileName);
                    BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out))) {
                for (String s : list) {

                    write.write(s+"\r\n");
                }
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
