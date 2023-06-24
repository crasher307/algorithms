package lesson2.homework;

import java.util.Random;

public class Main {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        int[] data = create(0, 99, 10); // Create random array
        var sort = new HeapSort(data); // TEST 10K ~ 11.6 sec
        sort.viewLogs();
    }

    /**
     * Сгенерировать массив с ранд-ми значениями
     *
     * @param min   мин-ое значение (вкл-но)
     * @param max   макс-ое значение (вкл-но)
     * @param count кол-во эл-ов
     * @return массив целых чисел
     */
    @SuppressWarnings("SameParameterValue")
    private static int[] create(int min, int max, int count) {
        int[] num = new int[count];
        for (int i = 0; i < count; i++) {
            num[i] = min + rand.nextInt(max - min + 1);
        }
        return num;
    }
}
