package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
            String path1 = read.readLine();
            String path2 = read.readLine();
            String content1 = new String(Files.readAllBytes(Paths.get(path1))); //читайте Хорстмана, он подсказал
            String content2 = new String(Files.readAllBytes(Paths.get(path2)));
            read.close();
            allLines = new ArrayList<String>(Arrays.asList(content1.split("\n"))); //просто записать в ссылку allLines массив нельзя, иначе выбрасывает UnsupportedOperationException, потому что asList возвращает лист фиксированного размера и все методы которые пытаются его изменить не поддерживаются. 
            forRemoveLines = new ArrayList<String>(Arrays.asList(content2.split("\n")));

            //for(String s : allLines)System.out.println(s);
            //for(String s : forRemoveLines) System.out.println(s);

            new Solution().joinData();

            //System.out.println(allLines);
            //for(String s : allLines)System.out.println(s);
            //for(String s : forRemoveLines) System.out.println(s);

        }catch (IOException e){
            System.out.println("Бросает корапшн ексепшн");
        }



    }

    public void joinData () throws CorruptedDataException {
        int countString = forRemoveLines.size();
        for(int i = 0; i < forRemoveLines.size(); i++){   //вот что бывает, когда посмотрел методы ArrayList, а методы List не посмотрел и не вспомнил про containsAll
            if(allLines.contains(forRemoveLines.get(i))){
                countString--;
            }
        }
        if(countString == 0){
            allLines.removeAll(forRemoveLines);
        }else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
