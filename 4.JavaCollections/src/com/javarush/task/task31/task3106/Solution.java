package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002

Требования:
1. В методе main нужно создать ZipInputStream для архива, собранного из кусочков файлов. Файлы приходят аргументами в main, начиная со второго.
2. Создай поток для записи в файл, который приходит первым аргументом в main. Запиши туда содержимое файла из архива.
3. Поток для чтения из архива должен быть закрыт.
4. Поток для записи в файл должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        String[] parts = new String[args.length-1];
        for (int i = 1; i < args.length; i++){
            parts[i-1] = args[i];
        }
        Arrays.sort(parts);
        List<InputStream> inputStreams = new ArrayList<>();
        for(String s : parts){
            inputStreams.add(new FileInputStream(s));
        }
        Vector<InputStream> vector = new Vector<>(inputStreams);
        Enumeration e = vector.elements();
        ZipInputStream zin = new ZipInputStream(new SequenceInputStream(e));
        ZipEntry z = zin.getNextEntry();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int c;
        byte[] buff = new byte[1024];
        while ((c=zin.read(buff)) > 0){
            bytes.write(buff, 0, c);
        }
        zin.closeEntry();
        zin.close();

        OutputStream out = new FileOutputStream(resultFileName);
        out.write(bytes.toByteArray());
        out.close();
    }
}
