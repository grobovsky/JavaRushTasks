package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.
Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.

Затем посчитай и выведи следующую информацию:
Всего папок - [количество папок в директории и поддиректориях]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.

Требования:
1. Метод main должен считывать путь к папке с консоли.
2. Если введенный путь не является директорией - нужно вывести "[полный путь] - не папка" и завершить работу.
3. Используй только классы и методы из пакета java.nio.
4. На консоль должна быть выведена следующая информация: "Всего папок - [количество папок в директории и поддиректориях]".
5. На консоль должна быть выведена следующая информация: "Всего файлов - [количество файлов в директории и поддиректориях]".
6. На консоль должна быть выведена следующая информация: "Общий размер - [общее количество байт, которое хранится в директории]".
*/

public class Solution extends SimpleFileVisitor<Path> {

    static int countFolder = -1;
    static int countFiles = 0;
    static long totalSize = 0;

    @Override
    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isDirectory()) {
            countFolder++;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(Files.isRegularFile(file)) {
            countFiles++;
            totalSize += Files.size(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String name = read.readLine();
        Path path = Paths.get(name);
        if (!Files.isDirectory(path)) {
            System.out.println(path.toAbsolutePath() + " - не папка");
            return;
        }
        Solution obj = new Solution();
        Files.walkFileTree(path, obj);
        System.out.println("Всего папок - " + countFolder);
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + totalSize);
    }
}
