package lesson4.homework;

import java.util.Scanner;

public class BinaryTree {
    private static Node root = null;
    public static void main(String[] args) {
        BinaryTree node = new BinaryTree();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Введите целое число");
            root = node.insert(root, scan.nextInt());

            System.out.println("root = " + root.value);
            node.inorder(root, null);

            System.out.println(root);

            System.out.println("\nВы хотите продолжить? (введите y или n)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    Node rotateLeft(Node node) {
        System.out.println("Поворот влево"); // против часовой

        Node child = node.right;
        Node childLeft = child.left;

        child.left = node;
        node.right = childLeft;

        return child;
    }
    Node rotateRight(Node node) {
        System.out.println("Поворот вправо"); // по часовой

        Node child = node.left;
        Node childRight = child.right;

        child.right = node;
        node.left = childRight;

        return child;
    }
    boolean isRed(Node node) {
        if (node == null) return false;
        return node.isRed;
    }
    void swapColors(Node first, Node second) {
        boolean tmp = first.isRed;
        first.isRed = second.isRed;
        second.isRed = tmp;

    }
    void recolor(Node node) {
        if (node == null) return;
        if (this.isRed(node)) {
            if (node.left != null) node.left.isRed = false;
            if (node.right != null) node.right.isRed = false;
        } else {
            if (node.left != null) node.left.isRed = true;
        }
        this.recolor(node.left);
        this.recolor(node.right);
    }
    Node insert(Node node, int data) {
        if (node == null) return (new Node(data)).setRoot();
        if (data == node.value) return node;
        if (data < node.value) node.left = this.insert(node.left, data);
        else node.right = this.insert(node.right, data);
        // правый - красный, левый - черный/не сущ.
        if (this.isRed(node.right) && !this.isRed(node.left)) node = this.rotateLeft(node);
        // левый и левый.левый - красный
        if (this.isRed(node.left) && this.isRed(node.left.left)) node = this.rotateRight(node);
        // перекраска
        this.recolor(root);
        return node;
    }
    void inorder(Node node, Node parent) {
        if (node != null) {
            this.inorder(node.left, node);
            this.inorder(node.right, node);
        }
    }

    static class Node {
        int value;
        Node left = null;
        Node right = null;
        boolean isRed = true;

        Node(int data) {
            this.value = data;
        }
        public Node setRoot() {
            this.isRed = false;
            return this;
        }
        private String view(int countTab) {
            String tab = countTab <= 0 ? "" : String.format("%" + countTab + "s", "").replace(" ", "|\t");
            return String.format(
                    "%s%s\n\t%sleft: %s\n\t%sright: %s",
                    this.value, this.isRed ? "_RED" : "_BLACK",
                    tab, this.left == null ? null : this.left.view(countTab + 1),
                    tab, this.right == null ? null : this.right.view(countTab + 1)
            );
        }
        public String toString() {
            return this.view(0);
        }
    }
}
