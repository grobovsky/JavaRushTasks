package com.javarush.task.task19.task1904;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер

Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
В классе адаптере создать приватное финальное поле Scanner fileScanner. 
Поле инициализировать в конструкторе с одним аргументом типа Scanner.

Данные в файле хранятся в следующем виде:

Иванов Иван Иванович 31 12 1950
Петров Петр Петрович 31 12 1957

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные только одного человека.
*/

public class Solution {

    public static void main(String[] args) throws IOException{
        //test
        /*InputStream stream = new FileInputStream("C:\\Users\\Anna\\Documents\\JavaRushTasks\\file3.txt");
        Scanner in = new Scanner(stream);
        PersonScannerAdapter scan = new PersonScannerAdapter(in);
        Person p = scan.read();
        String s = p.toString();
        System.out.println(s);*/
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private final Scanner fileScanner;
        PersonScannerAdapter(Scanner scan){
            fileScanner = scan;
        }

        @Override
        public Person read() throws IOException {
            String s = this.fileScanner.nextLine();
            String[] data = s.split(" ");
            String firstName = data[1];
            String lastName = data[0];
            String middleName = data[2];
            StringBuilder b = new StringBuilder();
            String date = b.append(data[3]).append(" ").append(data[4]).append(" ").append(data[5]).toString();
            DateFormat df = new SimpleDateFormat("dd MM yyyy");
            Date bithDay = null;
            try{
                bithDay = df.parse(date);
            }catch (ParseException e){
                e.printStackTrace();
            }
            Person p = new Person(firstName, middleName, lastName, bithDay);
            return p;
        }

        @Override
        public void close() throws IOException {
            this.fileScanner.close();
        }
    }
}
