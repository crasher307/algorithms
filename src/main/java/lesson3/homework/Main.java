package lesson3.homework;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add(1).add(2).add(3).add(4).add(5);
        list.view();
        list.reverse();
        list.view();
    }
}

