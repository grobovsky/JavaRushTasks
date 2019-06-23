package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(5)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    int size;
    int count;

    public CustomTree() {
        this.root = new Entry<>("root");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
        //return node(index).elementName;
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c) {
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


    private boolean isPisitionIndex(int index) {
        return index >= 0 && index < size;
    }

    Entry<String> node(int index) {
        Entry<String> x = root;
        if (!isPisitionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        if (x == null) {
            throw new NoSuchElementException();
        } else {
            LinkedList<Entry> q = new LinkedList<>();
            q.add(x);
            for (int i = 0; i <= index; i++) {
                x = q.poll();
                if (x.leftChild != null) {
                    q.add(x.leftChild);
                }
                if (x.rightChild != null) {
                    q.add(x.rightChild);
                }
            }
        }
        return x;
    }

    public String getDataNode(int index) {
        Entry<String> x = node(index);
        return x.elementName;
    }

    private void traversal(Entry n) {
        if (n == null) {
            return;
        }
        traversal(n.leftChild);
        traversal(n.rightChild);
        System.out.println(n.elementName + " " + n.availableToAddLeftChildren + " | " + n.availableToAddRightChildren);
    }


    public void displayTree() {
        Entry<String> x = root;
        if (x == null) {
            System.out.println("empty");
            return;
        }
        traversal(x);
    }

    private Entry entry(String value) {
        Entry x = root;
        /*if (x == null) {
            throw new NoSuchElementException();
        }*/
        LinkedList<Entry> q = new LinkedList<>();
        q.add(x);
        while (!q.isEmpty()) {
            x = q.poll();
            if (x.elementName.equals(value)) {
                return x;
            } else if (x.leftChild != null) {
                q.add(x.leftChild);
            }
            if (x.rightChild != null) {
                q.add(x.rightChild);
            }
        }
        return null;
    }

    private void countSubTree(Entry e) {
        if (e != null) {
            count++;
            countSubTree(e.leftChild);
            countSubTree(e.rightChild);
        }
    }

    public boolean remove(Object o) {
        count = 0;
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        Entry x = entry(s);
        if (x != null) {
            Entry p = x.parent;
            if (p.leftChild != null) {
                if (p.leftChild.elementName.equals(s)) {
                    p.leftChild = null;
                    p.availableToAddLeftChildren = false;
                    countSubTree(x);
                    size = size - count;
                }
            }
            if (p.rightChild != null) {
                if (p.rightChild.elementName.equals(s)) {
                    p.rightChild = null;
                    p.availableToAddRightChildren = false;
                    countSubTree(x);
                    size = size - count;
                }
            }
            x.parent = null;
        } else {
            return false;
        }
        return true;
    }    

    private void entryUp(Entry e) {
        if (e != null) {
            if (!e.availableToAddLeftChildren) {
                e.availableToAddLeftChildren = true;
            }
            if (!e.availableToAddRightChildren) {
                e.availableToAddRightChildren = true;
            }
            entryUp(e.leftChild);
            entryUp(e.rightChild);
        }
    }

    public boolean add(String s) {
        if (!adds(s)) {
            entryUp(this.root);
            adds(s);
        }
        return true;
    }

    private boolean adds(String s) {
        Entry<String> newEntry = new Entry(s);
        Entry<String> x = root;
        LinkedList<Entry> q = new LinkedList<>();
        q.add(x);
        while (!q.isEmpty()) {
            x = q.poll();
            if (x.leftChild != null) {
                q.add(x.leftChild);
            } else if (x.availableToAddLeftChildren) {
                x.leftChild = newEntry;
                x.leftChild.parent = x;
                size++;
                return true;
            }
            if (x.rightChild != null) {
                q.add(x.rightChild);
            } else if (x.availableToAddRightChildren) {
                x.rightChild = newEntry;
                x.rightChild.parent = x;
                size++;
                return true;
            }
        }
        return false;
    }

    public String getParent(String s) {
        Entry<String> x = root;
        String res = "null";
        if (x == null) {
            throw new NoSuchElementException();
        } else {
            LinkedList<Entry> q = new LinkedList<>();
            q.add(x);
            while (!q.isEmpty()) {
                x = q.poll();
                if (x.elementName.equals(s)) {
                    res = x.parent.elementName;
                    return res;
                }
                if (x.leftChild != null) {
                    q.add(x.leftChild);
                }
                if (x.rightChild != null) {
                    q.add(x.rightChild);
                }
            }
        }
        return res;
    }
    
    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String s) {
            this.elementName = s;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
