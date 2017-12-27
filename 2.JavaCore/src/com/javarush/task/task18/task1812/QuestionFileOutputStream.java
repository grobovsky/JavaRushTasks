package com.javarush.task.task18.task1812;

import java.io.*;
import java.util.Scanner;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {

    private AmigoOutputStream quest;

        public QuestionFileOutputStream (AmigoOutputStream quest){
            this.quest = quest;
        }

        @Override
        public void close() throws IOException {
            System.out.println("Вы действительно хотите закрыть поток? Д/Н");
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            if(str.equals("Д")){
                quest.close();
            }else return;
        }


    @Override
    public void flush() throws IOException {
        quest.flush();
    }

    @Override
    public void write(int b) throws IOException {
        quest.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        quest.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        quest.write(b, off, len);
    }


}

