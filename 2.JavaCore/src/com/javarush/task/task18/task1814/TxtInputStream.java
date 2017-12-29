package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    
    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        super(fileName);
        int ind = fileName.lastIndexOf('.');
        String txt = fileName.substring(ind);
        if(!txt.equals(".txt")){
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedFileNameException{
        //new TxtInputStream("C:\\Users\\Anna\\Documents\\JavaRushTasks\\file1.txt");
    }
}

