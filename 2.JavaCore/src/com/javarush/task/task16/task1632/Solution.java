package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new firstThread());
        threads.add(new secondThread());
        threads.add(new thridThread());
        threads.add(new fourthThread());
        threads.add(new fifthtThread());
    }

    public static void main(String[] args) throws InterruptedException {
        /*threads.get(4).start();
        threads.get(4).join();*/
    }

    public static class firstThread extends Thread {
        public void run(){
            while(true){

            }
        }
    }
    public static class secondThread extends Thread {
        public void run(){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }
    public static class thridThread extends Thread {
        public void run(){
            try{
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static class fourthThread extends Thread implements Message {
        public void showWarning(){
            if(this.isAlive()){
                return;
            }
        }
        public void run(){

        }
    }
    public static class fifthtThread extends Thread {
        public void run(){
            try(BufferedReader read = new BufferedReader(new InputStreamReader(System.in))){
                int sum = 0;
                while (true){
                    String s = read.readLine();
                    if(s.equals("N"))break;
                   try{
                       sum = sum + Integer.parseInt(s);
                   }catch (NumberFormatException e){
                       System.out.println("это не число!");
                       return;
                   }
                }
                System.out.println(sum);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
