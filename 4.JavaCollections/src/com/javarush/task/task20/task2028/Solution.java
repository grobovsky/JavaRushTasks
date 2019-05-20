package com.javarush.task.task20.task2028;

/* Амиго, похоже ты уже достаточно окреп. Самое время проверить свои навыки в большой задаче!
Сегодня реализуем свое дерево немного нестандартным способом(на базе AbstractList).
Вводную информацию можешь получить используя свой любимый поисковик и текст ниже.
Элементы дерева должны следовать так как показано на картинке:
Для начала сделаем наше дерево потомком класса AbstractList с параметром типа String, а также реализуем интерфейсы Cloneable и Serializable.

Реализацию методов get(int index) и size() пока оставь стандартной.
Требования:
    Класс CustomTree должен поддерживать интерфейс Cloneable.
    Класс CustomTree должен поддерживать интерфейс Serializable.
    Класс CustomTree должен быть потомком класса AbstractList<String>.
*/

public class Solution {
    public static void main(String[] args) {

        CustomTree tree = new CustomTree();
        tree.add("0");
        tree.add("1");
        tree.add("2");
        tree.add("3");
        tree.add("4");
        tree.add("5");
        tree.add("6");

        //всякие тестовые методы
        //System.out.println(tree.getDataNode(4));
        tree.displayTree();
        System.out.println();

        //tree.remove(1);
        //tree.displayTree();
