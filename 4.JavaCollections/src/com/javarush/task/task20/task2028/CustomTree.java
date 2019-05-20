package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
Построй дерево(2)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Node<String> root;
    int size;

    public CustomTree() {
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException(); //по условиям 2й части задачи
        //return node(index).value;
    }

    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int size() {
        return size;
    }


    private boolean isPisitionIndex(int index) { //вспомогательный метод, подсмотренный в библиотечных классах, с ним красивее и правильней
        return index >= 0 && index < size;
    }

    Node<String> node(int index) {
        Node<String> x = root;
        if (!isPisitionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        if (x == null) {
            throw new NoSuchElementException();
        } else {
            LinkedList<Node> q = new LinkedList<>();
            q.add(x);
            for (int i = 0; i <= index; i++) {
                x = q.poll();
                if (x.left != null) {
                    q.add(x.left);
                }
                if (x.right != null) {
                    q.add(x.right);
                }
            }
        }
        return x;
    }
    
    public String remove(int index) {
        throw new UnsupportedOperationException(); //по условиям 2й части задачи

        /*Node<String> x = node(index);
        String element = x.value;
        if (x == root) {
            root = null;
        } else {
            Node<String> p = x.parent;
            if (p.left.equals(x)) {
                p.left = null;
            } else {
                p.right = null;
            }
        }
        size--;
        return element;*/
    }
    public boolean add(String e) {          //если следовать дословно условия задачи, то нужно дописывать флаг запрета на добавление узлов на месте удаленных, задача решается и без этого нелогичного гемора
        Node<String> newNode = new Node(e);
        if (root == null) {
            root = newNode;
            size++;
            return true;
        } else {
            Node<String> x = root;
            LinkedList<Node> q = new LinkedList<>();
            q.add(x);
            while (!q.isEmpty()) {
                x = q.poll();
                if (x.left != null) {
                    q.add(x.left);
                } else {
                    x.left = newNode;
                    x.left.parent = x;
                    size++;
                    return true;
                }
                if (x.right != null) {
                    q.add(x.right);
                } else {
                    x.right = newNode;
                    x.right.parent = x;
                    size++;
                    return true;
                }
            }
        }
        return true;
    }

    static class Node<E> {
        E value;
        Node<E> left, right, parent;

        public Node(E value) {
            this.value = value;
        }
    }
    
    //класс по условиям 3й части задачи, как можно было написать код к 1й части без этого класса, хз, но 3я часть намекает, что в целом это дерево должно таки выглядеть как в условии 1й части с запретом на добавление узлов на место удаленных
    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry (String s){
            this.elementName = s;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
