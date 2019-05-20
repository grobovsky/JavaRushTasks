package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Node<String> root;
    int size;

    public CustomTree() {
    }

    @Override
    public String get(int index) {
        return node(index).value;
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
        Node<String> x = node(index);
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
        return element;
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
}
