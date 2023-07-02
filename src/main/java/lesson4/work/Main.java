package lesson4.work;

import lombok.AllArgsConstructor;

public class Main {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        for (int i = 1; i <= 5; i++) {
            map.push(i, i);
        }
        // map.remove(3);
        // System.out.println(Arrays.toString(map.baskets));
        System.out.println(map.find(3));
        System.out.println(map.find(6));


        BinaryTree binaryTree = new BinaryTree();
        binaryTree.print();
    }
}

class HashMap {
    private static final int INIT_SIZE = 16;
    private Basket[] baskets;

    public HashMap(int size) {
        this.baskets = new Basket[size];
    }
    public HashMap() {
        this(INIT_SIZE);
    }

    private int getIndex(int key) { // O(1)
        return key % this.baskets.length; // [0 .. baskets.length - 1]
    }
    public Integer find(int key) {
        Basket basket = this.baskets[getIndex(key)];
        return basket == null ? null : basket.find(key);
    }
    public boolean push(int key, int value) {
        int idx = getIndex(key);
        if (this.baskets[idx] == null) this.baskets[idx] = new Basket();
        return this.baskets[idx].push(key, value);
    }
    public boolean remove(int key) {
        return this.baskets[getIndex(key)].remove(key);
    }

    private static class Basket {
        private Node head;

        public Integer find(int key) { // O(N) -> O(1)
            Node node = this.head;
            while (node != null) {
                if (node.entity.key == key) return node.entity.value;
                node = node.next;
            }
            return null;
        }
        public boolean push(int key, int value) {
            Node node = new Node(key, value);
            if (this.head == null) {
                this.head = node;
            } else {
                Node current = this.head;
                while (current.next != null) {
                    if (current.entity.key == key) return false;
                    current = current.next;
                }
                current.next = node;
            }
            return true;
        }
        public boolean remove(int key) {
            if (this.head == null) return false;
            if (this.head.entity.key == key) {
                this.head = this.head.next;
                return true;
            }
            Node node = this.head;
            while (node.next != null) {
                if (node.next.entity.key == key) {
                    node.next = node.next.next;
                    return true;
                }
                node = node.next;
            }
            return false;
        }

        @AllArgsConstructor
        private static class Node {
            private Entity entity;
            private Node next;

            public Node(Entity entity) {
                this(entity, null);
            }
            public Node(int key, int value, Node next) {
                this(new Entity(key, value), next);
            }
            public Node(int key, int value) {
                this(new Entity(key, value), null);
            }

            @AllArgsConstructor
            private static class Entity {
                private int key;
                private int value;
            }
        }
    }
}

class BinaryTree {
    Node root;

    public boolean find(int value) { // O(log N)
        Node current = this.root;
        while (current != null) {
            if (current.value == value) return true;
            current = current.value > value ? current.left : current.right;
        }
        return false;
    }

    public void print(){
        print(this.root);
    }

    private Integer print(Node node){
        if(node == null) return null;
        System.out.printf("%s [L: %s, R: %s]\n", node.value, this.print(node.left), this.print(node.right));
        return node.value;
    }

    private static class Node {
        int value;
        Node left, right;
    }
}