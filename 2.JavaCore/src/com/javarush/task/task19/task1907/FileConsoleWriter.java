package com.javarush.task.task19.task1917;

/* 
Свой FileWriter

Реализовать логику FileConsoleWriter.
Класс FileConsoleWriter должен содержать приватное поле FileWriter fileWriter.
Класс FileConsoleWriter должен содержать все конструкторы, которые инициализируют fileWriter для записи.
Класс FileConsoleWriter должен содержать пять методов write и один метод close:

public void write(char[] cbuf, int off, int len) throws IOException
public void write(int c) throws IOException
public void write(String str) throws IOException
public void write(String str, int off, int len)
public void write(char[] cbuf) throws IOException
public void close() throws IOException
При записи данных в файл, должен дублировать эти данные на консоль.
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter {
    private FileWriter fileWriter;
    public FileConsoleWriter (String filename) throws IOException {
        fileWriter = new FileWriter(filename);
    }
    public FileConsoleWriter (String filename, boolean append) throws IOException {
        fileWriter = new FileWriter(filename, append);
    }
    public FileConsoleWriter (File file) throws IOException {
        fileWriter = new FileWriter(file);
    }
    public FileConsoleWriter (File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }
    public FileConsoleWriter (FileDescriptor fd) throws IOException {
        fileWriter = new FileWriter(fd);
    }
    public static void main(String[] args) throws IOException {
        /*FileConsoleWriter out = new FileConsoleWriter("D:\\Java_Rush\\project\\file1.txt");
        char[] ch = {'a', 'b', 'c', 'd', 'e'};
        String s = "stroka";
        out.write(ch, 3, 2);
        out.close();*/
    }
// println не принимает валидатор
    public void write(char[] cbuf, int off, int len) throws IOException {
        fileWriter.write(cbuf, off, len);
        for(int i = off; i < off+len; i++) System.out.print(cbuf[i]);
    }
    public void write(int c) throws IOException {
        fileWriter.write(c);
        System.out.print(c);
    }
    public void write(String str) throws IOException {
        fileWriter.write(str);
        System.out.print(str);
    }
    public void write(String str, int off, int len) throws IOException {
        fileWriter.write(str, off, len);
        char[] ch = str.toCharArray();
        for(int i = off; i < off+len; i++) System.out.print(ch[i]);
    }
    public void write(char[] cbuf) throws IOException {
        fileWriter.write(cbuf);
        for(char ch : cbuf) System.out.print(ch);
    }
    public void close() throws IOException {
        fileWriter.close();
    }

}
