package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов

1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2. Переименовать resultFileAbsolutePath в 'allFilesContent.txt' (используй метод FileUtils.renameFile, и, если понадобится, FileUtils.isExist).
2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".

Требования:
1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
2. Нужно создать поток для записи в переименованный файл.
3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
4. Поток для записи в файл нужно закрыть.
5. Не используй статические переменные.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String path = args[0]; 
        String resultFileAbsolutePath = args[1]; 
        File result = new File(resultFileAbsolutePath);
        String pathNewFile = result.getParent() + "/allFilesContent.txt";
        File newFile = new File(pathNewFile);
        if (FileUtils.isExist(result)) {
            FileUtils.renameFile(result, newFile);
        }

        List<File> filesList = new ArrayList<>();
        File folder = new File(path);
        allFiles(folder, filesList);
        Collections.sort(filesList);

        FileOutputStream out = new FileOutputStream(newFile, true);
        int a;
        for (File f : filesList) {
            if (f.length() <= 50) {
                FileInputStream in = new FileInputStream(f);
                while ((a = in.read()) != -1) {
                    out.write(a);
                }
                //out.write('\r');
                out.write('\n');
                in.close();
            }
        }
        out.flush();
        out.close();
    }

    public static void allFiles(File folder, List<File> list){
        File[] files = folder.listFiles();
        for (File f : files){
            if(f.isDirectory()){
                allFiles(f, list);
                continue;
            }
            list.add(f);
        }
    }
}
