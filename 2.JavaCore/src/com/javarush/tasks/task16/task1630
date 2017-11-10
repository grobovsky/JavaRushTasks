package com.javarush.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static String firstFileName; // = "C:\\file.txt"; - для теста
    public static String secondFileName; // = "C:\\file1.txt";

    static{
        try (BufferedReader read = new BufferedReader(new InputStreamReader(System.in))){
            firstFileName = read.readLine();
            secondFileName = read.readLine();
        }catch (IOException e){
            System.out.println("ошибка");
        }
    }//add your code here - добавьте код тут

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread  extends Thread implements ReadFileInterface /*, Runnable*/ {
        private String fileName;
        private String fileContent;
        //private Thread t; //без наследования от Thread с реализацией Runnable

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
        public String getFileContent(){
            if(fileContent == null)return "";
            else return fileContent;
        }
        /*public void join() throws InterruptedException {  //без наследования от Thread с реализацией Runnable
           t.join();
        }
        public void start(){
            t = new Thread(this, fileName);
            t.start();
            System.out.println(t.getName());
        }*/
        public void run(){ //через жопу, но очень уж хотелось извратиться с чтением строки и не скливать байты или чары
            try(BufferedReader read = new BufferedReader(new FileReader(fileName))){

                int c;
                fileContent = "";
                while ((c = read.read()) != -1){
                    fileContent = fileContent + (char)c + read.readLine() +  " ";
                }
                read.close();
            }catch (IOException e){
                e.getStackTrace();
            }

        }
    }//add your code here - добавьте код тут
}
