package com.javarush.task.task20.task2028;
/*
Построй дерево(5)
Добавлять в дерево элементы мы можем, теперь займись удалением:
Необходимо реализовать метод remove(Object o), который будет удалять элемент дерева имя которого было полученного в качестве параметра.
Если переданный объект не является строкой, метод должен бросить UnsupportedOperationException.
Если в дереве присутствует несколько элементов с переданным именем - можешь удалить только первый найденный.
Не забывай сверять поведение своего дерева с картинкой:

Что будет если удалить из дерева элементы "3", "4", "5" и "6", а затем попытаемся добавить новый елемент?

В таком случае элементы "1" и "2" должны восстановить возможность иметь потомков (возможно придется внести изменения в метод add()).

Требования:
1. После удаления последнего добавленного элемента из дерева с помощью метода remove, метод size должен возвращать N-1.
2. После удаления второго элемента добавленного в дерево, метод size должен возвращать N/2 + 1 (для случаев где N > 2 и является степенью двойки), N - размер дерева до удаления.
3. Если переданный объект не является строкой, метод remove() должен бросить UnsupportedOperationException.
4. Если ни один элемент не способен иметь потомков, необходимо восстановить такую возможность.
*/

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> list = new CustomTree();

        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

        //((CustomTree)list).displayTree();
        //System.out.println(((CustomTree)list).countSubTree(((CustomTree)list).root));

        System.out.println("The list size is " + list.size());
        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));

        list.remove("3");
        System.out.println("SIZE:" + ((CustomTree) list).size);
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("8"));

        list.add("16");
        System.out.println("The expected parent is 9. The actual parent is " + ((CustomTree) list).getParent("16"));

        list.remove("4");
        list.remove("5");
        list.remove("6");
        System.out.println("Expected: true. Actual: " + list.add("20"));
        System.out.println("The expected parent is 1. The actual parent is " + ((CustomTree) list).getParent("20"));        
    }
}
