package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";

    private FileOutputStream amigo;

    public AmigoOutputStream(FileOutputStream amigo) throws FileNotFoundException {
        super(fileName);  \\ на момент выполнения нихера не понял почему нельзя сделать конструктор без вызова родительского и тупо подобрал, BafferedOutputStream констурируется спокойно без обязательноговызова родительского конструктора..
        this.amigo = amigo;
    }

    @Override
    public void write(byte[] b) throws IOException {
        amigo.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        amigo.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        amigo.write(b);
    }

    @Override
    public void flush() throws IOException {
        amigo.flush();
    }

    @Override
    public void close() throws IOException {
        this.flush();
        String s = "JavaRush © All rights reserved.";
        this.write(s.getBytes());
        amigo.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
