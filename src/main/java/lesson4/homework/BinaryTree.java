package lesson4.homework;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryTree {
    private static final Scanner in = new Scanner(System.in);
    private static final BinaryTree node = new BinaryTree();
    private static Node root = null;

    public static void main(String[] args) {
        test();
        init();
    }

    public static void init() {
        while (true) {
            try {
                System.out.print("Введите целое число: ");
                root = node.add(root, Integer.parseInt(in.next()));
                System.out.printf("\n%s\nПродолжаем? (y/n): ", root);
                if (!Arrays.asList('Y', 'y').contains(in.next().charAt(0))) return; // EXIT
            } catch (Exception e) {
                System.out.println("Error: Попробуйте снова");
            }
        }
    }
    public static void test() {
        for (int i = 1; i <= 20; i++) root = node.add(root, i);
        System.out.println(root);
    }

    boolean isRed(Node node) {
        return node != null && node.isRed;
    }

    private Node toLeft(Node node) {
        // System.out.println("Поворот влево"); // против часовой
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }
    private Node toRight(Node node) {
        // System.out.println("Поворот вправо"); // по часовой
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }
    private void changeColors(Node node) {
        node.setColor(!node.isRed);
        node.left.setColor(!node.left.isRed);
        node.right.setColor(!node.right.isRed);
    }

    Node add(Node node, int value) {
        if (node == null) return new Node(value);

        if (value != node.value) {
            if (value < node.value) node.left = this.add(node.left, value);
            else node.right = this.add(node.right, value);
            if (this.isRed(node.right) && !this.isRed(node.left)) node = this.toLeft(node);
            if (this.isRed(node.left) && this.isRed(node.left.left)) node = this.toRight(node);
        }

        if (this.isRed(node.left) && this.isRed(node.right)) this.changeColors(node);

        return node;
    }

    static class Node {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String NULL_DATA = ANSI_BLACK + "NULL_BLACK" + ANSI_RESET;

        int value;
        Node left = null;
        Node right = null;
        boolean isRed = root != null;

        Node(int value) {
            this.value = value;
        }

        public void setColor(boolean isRed) {
            this.isRed = this != root && isRed;
        }

        public String toString() {
            return this.view(0);
        }
        private String view(int countTab) {
            String tab = countTab <= 0 ? "" : String.format("%" + countTab + "s", "").replace(" ", "|\t");
            return String.format(
                    "%s%s%s%s\n\t%sleft: %s\n\t%sright: %s",
                    this.isRed ? ANSI_RED : ANSI_BLACK,
                    this.value, this.isRed ? "_RED" : "_BLACK",
                    ANSI_RESET,
                    tab, this.left == null ? NULL_DATA : this.left.view(countTab + 1),
                    tab, this.right == null ? NULL_DATA : this.right.view(countTab + 1)
            );
        }
    }
}
