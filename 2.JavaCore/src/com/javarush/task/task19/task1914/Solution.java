package com.javarush.task.task19.task1914;

/* 
Решаем пример

В методе main подмени объект System.out написанной тобой ридер-оберткой по аналогии с лекцией.
Твоя ридер-обертка должна выводить на консоль решенный пример.
Вызови готовый метод printSomething(), воспользуйтесь testString.
Верни переменной System.out первоначальный поток.

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream ps = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);

        testString.printSomething();

        System.setOut(ps);
        String strOut = out.toString();
        strOut = strOut.replace("\r\n", "");
        String[] words = strOut.split("\\D+");
        int a = Integer.parseInt(words[0]);
        int b = Integer.parseInt(words[1]);
        int c = 0;
        if(strOut.contains("+")){
            c = a+b;
        }else if(strOut.contains("-")){
            c = a-b;
        }else if(strOut.contains("*")){
            c = a*b;
        }
        System.out.println(strOut+c);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

