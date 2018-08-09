package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
Прочитать в дополнительных материалах о сериализации графов.
Дан ориентированный плоский граф Solution, содержащий циклы и петли.
Пример, http://edu.nstu.ru/courses/saod/images/graph1.gif

Сериализовать Solution.
Все данные должны сохранить порядок следования.

Требования:
1. В классе Solution должно существовать поле edges.
2. В классе Solution должно существовать поле node.
3. Тип поля node должен быть int.
4. Класс Solution должен поддерживать интерфейс Serializable.
*/

public class Solution implements Serializable{
    int node;
    List<Solution> edges = new LinkedList<>();

    public Solution(int node) {
        this.node = node;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution sol = new Solution(0);
        sol.edges.add(new Solution(1));
        sol.edges.add(new Solution(2));
        sol.edges.add(new Solution(3));

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bo);
        out.writeObject(sol);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bo.toByteArray()));
        Solution deser = (Solution) in.readObject();
        for(Solution list : deser.edges){
            System.out.println(list.node);
        }

    }
}
