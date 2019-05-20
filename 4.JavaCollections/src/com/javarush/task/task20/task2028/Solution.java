package com.javarush.task.task20.task2028;

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
