package com.javarush.task.task19.task1915;

/* 
Дублируем текст

Считай с консоли имя файла.
В методе main подмени объект System.out написанной тобой ридер-оберткой по аналогии с лекцией.
Твоя ридер-обертка должна выводить весь текст и на консоль и в файл, имя которого ты считал.
Вызови готовый метод printSomething(), воспользуйся testString.
Верни переменной System.out первоначальный поток.
Закрой поток файла.

Пример вывода на экран:
it's a text for testing
Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException{
        PrintStream ps = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);

        testString.printSomething();

        System.setOut(ps);
        String res = out.toString();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file = read.readLine();
        read.close();
        FileOutputStream write = new FileOutputStream(file);
        write.write(out.toByteArray());
        write.close();
        System.out.println(res);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

