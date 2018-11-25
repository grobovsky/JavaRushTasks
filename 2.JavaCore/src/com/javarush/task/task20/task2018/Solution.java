package com.javarush.task.task20.task2018;

import java.io.*;


/* 
Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найди проблему и исправь ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
В сигнатуре класса В ошибки нет :).
В методе main ошибок нет.

Требования:
1. Класс B должен быть потомком класса A.
2. Класс B должен поддерживать интерфейс Serializable.
3. Класс A не должен поддерживать интерфейс Serializable.
4. Класс A не должен поддерживать интерфейс Externalizable.
5. Программа должна выполняться без ошибок.
6. При десериализации должны корректно восстанавливаться значение полей nameA и nameB.
*/

public class Solution implements Serializable { //должен быть сериализуемым, что бы сериализовывать иннер классы
    public static class A {

        protected String nameA = "A";


        public A(){ 
        }
        public A(String nameA) {
            this.nameA += nameA;
        }
    }

    public class B extends A implements Serializable {

        private String nameB;

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException { //запись всех полей
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(nameA);
            objectOutputStream.writeObject(nameB);
        }
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException { //чтение всех полей
            objectInputStream.defaultReadObject();
            nameA = (String) objectInputStream.readObject();
            nameB = (String) objectInputStream.readObject();
        }

        public B(){  //должен быть конструктор без параметров
        }
        public B(String nameA, String nameB) {
            super(nameA);
            this.nameA += nameA;
            this.nameB = nameB;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);

        Solution solution = new Solution();
        B b = solution.new B("B2", "C33");
        System.out.println("nameA: " + b.nameA + ", nameB: " + b.nameB);

        oos.writeObject(b);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(arrayInputStream);

        B b1 = (B)ois.readObject();
        System.out.println("nameA: " + b1.nameA + ", nameB: " + b1.nameB);
    }
}