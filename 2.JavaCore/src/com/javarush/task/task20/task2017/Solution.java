package com.javarush.task.task20.task2017;

import java.io.*;

/* 
Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.

Требования:
1. Класс B должен быть потомком класса A.
2. Класс A должен поддерживать интерфейс Serializable.
3. Класс B не должен явно поддерживать интерфейс Serializable.
4. Метод getOriginalObject должен возвращать объект типа A полученный из потока ObjectInputStream.
5. Метод getOriginalObject должен возвращать null, если при попытке десериализации не был получен объект типа A.
6. Метод getOriginalObject должен возвращать null, если при попытке десериализации возникло исключение.
*/

public class Solution implements Serializable {
    public A getOriginalObject(ObjectInputStream objectStream) {
        A a;
        try {
            a = (A) objectStream.readObject();
            if (a instanceof B) {
                return null;
            } else {
                return a;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) throws IOException {
        /*ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bo);
        Solution.A ser = new Solution().new A();
        out.writeObject(ser);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bo.toByteArray()));
        Solution res = new Solution();
        Solution.A des = res.getOriginalObject(in);
        System.out.println(des.getClass());*/

        
    }
}
