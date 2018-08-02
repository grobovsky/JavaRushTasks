package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
Класс Person должен сериализовываться с помощью интерфейса Externalizable.
Исправь ошибку сериализации.
Сигнатуры методов менять нельзя.

Требования:
1. В классе Solution.Person должен быть создан публичный конструктор без параметров.
2. В классе Solution.Person должен быть создан конструктор с тремя параметрами (String firstName, String lastName, int age).
3. Класс Solution.Person должен поддерживать интерфейс Externalizable.
4. Методы readExternal и writeExternal должны позволять корректно сериализовывать и десериализовывать объекты типа Person.
*/

public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException { \\порядок записи
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { \\порядок чтения
            mother = (Person)in.readObject();                                                  
            father = (Person)in.readObject();
            firstName = (String) in.readObject();           \\String нормально получилось прочитать только readObject
            lastName = (String) in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Person ser = new Person("Ivan", "Ivanov", 35);
        ser.setMother(new Person("Vera", "Ivanova", 49));
        ser.setFather(new Person("Petr", "Ivanov", 50));
        List<Person> child = new ArrayList<>();
        child.add(new Person("Vasya", "Ivanov", 9));
        ser.setChildren(child);


        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bo);
        ser.writeExternal(out);
        byte[] bytes = bo.toByteArray();
        bo.close();
        out.close();

        Person dst = new Person();
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bi);
        dst.readExternal(in);
        bi.close();
        in.close();

        System.out.println(dst.firstName);
        System.out.println(dst.lastName);
        System.out.println(dst.mother);
        System.out.println(dst.father);
        System.out.println(dst.children);
    }
}
