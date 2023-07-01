package lesson3.homework;

class LinkedList {
    private Node head;

    public LinkedList add(int value) {
        if (this.head == null) {
            this.head = new Node(value);
        } else {
            Node last = this.head;
            while (last.next != null) last = last.next;
            last.next = new Node(value);
        }
        return this;
    }

    public LinkedList view() {
        Node list = this.head;
        while (list.next != null) {
            System.out.printf("%s, ", list);
            list = list.next;
        }
        System.out.printf("%s\n", list);
        return this;
    }

    public LinkedList reverse() {
        Node current = this.head;
        Node prev = null;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
        return this;
    }

    static class Node {
        protected Node next;
        protected int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%d", value);
        }
    }
}
