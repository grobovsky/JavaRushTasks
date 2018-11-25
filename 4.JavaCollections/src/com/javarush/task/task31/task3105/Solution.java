package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/* 
Добавление файла в архив

В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.

Требования:
1. В методе main создай ZipInputStream для архивного файла (второй аргумент main). Нужно вычитать из него все содержимое.
2. В методе main создай ZipOutputStream для архивного файла (второй аргумент main).
3. В ZipOutputStream нужно записать содержимое файла, который приходит первым аргументом в main.
4. В ZipOutputStream нужно записать все остальное содержимое, которое было вычитано из ZipInputStream.
5. Потоки для работы с архивом должны быть закрыты.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName; 
        String zipPath;
        if (args.length != 0) {
            fileName = args[0];
            zipPath = args[1];
        } else {
            System.out.println("args empty");
            return;
        }
        //ZipFile zipFile = new ZipFile(zipPath); - хотелось без танцев через readAllBytes()
        Path file = Paths.get(fileName);
        ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath));

        Map<byte[], String> listBuffs = new LinkedHashMap<>();
        ZipEntry z;
        while ((z = zin.getNextEntry()) != null) {
            if (z.getName().equals("new/" + file.getFileName().toString())) {
                continue;
            }
            //InputStream in = zipFile.getInputStream(z);
            //byte[] bytes = in.readAllBytes(); - не проканало, валидатор не знает ReadAllBytes()
            int c;
            byte[] buff = new byte[1024];
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            while ((c = zin.read(buff)) > 0) {
                byteArray.write(buff, 0, c);
            }
            listBuffs.put(byteArray.toByteArray(), z.getName());
            //in.close();
            zin.closeEntry();
        }
        zin.close();
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath));
        zout.putNextEntry(new ZipEntry("new/" + (file.getFileName().toString())));
        Files.copy(file, zout);
        zout.closeEntry();
        for (Map.Entry<byte[], String> map : listBuffs.entrySet()) {
            zout.putNextEntry(new ZipEntry(map.getValue()));
            zout.write(map.getKey());
            zout.closeEntry();
        }
        zout.close();
    }
}
