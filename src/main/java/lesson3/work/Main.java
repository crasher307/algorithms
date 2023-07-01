package lesson3.work;

import java.util.Date;

class List {
    static Node head;

    static class Node {
        int value;
        Node next;
    }

    public static void pushFront(int value) { // O(1)
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }

    public static void popFront() { // O(1)
        if (head != null) {
            head = head.next;
        }
    }

    public static void print() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public static boolean contains(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public static void pushBack(int value) { // O(N)
        Node node = new Node();
        node.value = value;

        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }

            cur.next = node;
        }
    }

    public static void popBack() { // O(N)
        if (head != null) {
            if (head.next == null) {
                head = null;
            } else {
                Node cur = head;
                while (cur.next.next != null) {
                    cur = cur.next;
                }
                cur.next = null;
            }
        }
    }
}

class dList {
    static Node head;
    static Node tail;

    static class Node {
        int value;
        Node next;
        Node prev;
    }

    public static void pushFront(int value) { // O(1)
        Node node = new Node();
        node.value = value;

        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
        }
        head = node;
    }

    public static void popFront() { // O(1)
        if (head != null) {
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        }
    }

    public static void print() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public static boolean contains(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public static void pushBack(int value) { // O(1)
        Node node = new Node();
        node.value = value;

        if (tail == null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
    }

    public static void popBack() { // O(1)
        if (tail != null) {
            if (tail.prev == null) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
        }
    }

    public static void sort() { // O(N^2)
        // bubble sort
        // for(int i = 0; i < n; i++)
        // for(int j = 0; j < n - 1 - i; j++)
        // if(A[j] > A[j+1])
        // swap(A[j], A[j+1]);

        boolean needSort = true;
        do {
            needSort = false;
            Node node = head;
            while (node != null && node.next != null) {
                if (node.value > node.next.value) {
                    Node before = node.prev;
                    Node cur = node;
                    Node next = node.next;
                    Node after = next.next;

                    cur.next = after;
                    cur.prev = next;
                    next.next = cur;
                    next.prev = before;

                    if (before != null)
                        before.next = next;
                    else
                        head = next;

                    if (after != null)
                        after.prev = cur;
                    else
                        tail = cur;

                    needSort = true;
                }
                node = node.next;
            }

        } while (needSort);
    }
}

public class Main {
    public static void main(String[] args) {
        dList list = new dList();
        for (int i = 1; i <= 10; i++) {
            list.pushFront(i);
        }

        list.sort();

        list.print();


//        list.print();
//
//        list.popFront();
//        list.popFront();
//
//        list.print();
//
//        System.out.println(list.contains(2));
//        System.out.println(list.contains(5));
//
//        list.pushBack(7);
//
//        list.print();
//
//        list.popBack();
//
//        list.print();
    }
}
// FIFO = First In First Out - РћС‡РµСЂРµРґСЊ
// LIFO = Last In First Out - РЎС‚РµРє