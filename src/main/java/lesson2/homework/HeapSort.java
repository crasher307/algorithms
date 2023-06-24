package lesson2.homework;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HeapSort {
    private static final boolean debug = false;

    private static final String prefixSort = "\t\t";
    private static final String prefixThree = "\t\t\t";

    private final Log logsThree = new Log("Построение дерева");
    private final Log logsSort = new Log("Сортировка");
    private final Log logsRuntime = new Log("Затраты времени");

    private boolean viewLogs = true;
    private boolean viewRuntime = true;
    private boolean viewResult = true;

    private final int[] dataInitial;
    private final int[] data;

    public HeapSort(int[] data) {
        this.dataInitial = data;
        this.data = data.clone();

        long stamp = !this.viewRuntime ? 0 : (new Date()).getTime();
        this.sort(stamp); // Сортировка
        this.saveRuntime(stamp, "Весь процесс");

        if (HeapSort.debug) this.viewLogs(this.viewResult, this.viewRuntime, this.viewLogs);
    }

    public void viewLogs(boolean viewResult, boolean viewRuntime, boolean viewLogs) {
        this.viewLogs = viewLogs;
        this.viewRuntime = viewRuntime;
        this.viewResult = viewResult;
        if (this.viewLogs) {
            System.out.println();
            this.logsThree.show();
            System.out.println();
            this.logsSort.show();
        }
        if (this.viewRuntime) {
            System.out.println();
            this.logsRuntime.show();
        }
        if (this.viewResult) {
            System.out.println();
            System.out.printf("Начальный массив:\t\t\t%s\n", Arrays.toString(this.dataInitial));
            System.out.printf("Конечный массив:\t\t\t%s\n", Arrays.toString(this.data));
        }
    }
    public void viewLogs(boolean viewResult, boolean viewRuntime) {
        this.viewLogs(viewResult, viewRuntime, false);
    }
    public void viewLogs(boolean viewResult) {
        this.viewLogs(viewResult, this.viewRuntime, false);
    }
    public void viewLogs() {
        this.viewLogs(true, this.viewRuntime, false);
    }

    // *** Sort ***

    private void sort(long stamp) {
        this.initSort(this.initThree(stamp));
    }

    private long initThree(long stamp) {
        for (int i = this.data.length / 2 - 1; i >= 0; i--) { // Построение дерева
            this.three(i, this.data.length, this.logsThree);
        }
        return this.saveRuntime(stamp, "Построение дерева");
    }
    private long initSort(long stamp) {
        for (int i = this.data.length - 1; i >= 0; i--) { // Сортировка
            this.swap(i, 0, HeapSort.prefixSort, this.logsSort); // Меняем местами
            this.three(0, i, this.logsSort); // Запуск рекурсивной сортировки
        }
        return this.saveRuntime(stamp, "Сортировка");
    }

    private void three(int i, int len, Log logs) {
        int e_idx = i; // Корень
        int l_idx = i * 2 + 1; // Левый эл-т
        int r_idx = i * 2 + 2; // Правый эл-т
        if (l_idx < len && this.data[l_idx] > this.data[e_idx]) e_idx = l_idx; // Левый больше
        if (r_idx < len && this.data[r_idx] > this.data[e_idx]) e_idx = r_idx; // Правый больше
        if (e_idx != i) { // Больший эл-т изменился
            this.swap(i, e_idx, HeapSort.prefixThree, logs); // Меняем местами
            this.three(e_idx, len, logs); // Запуск рекурсивной сортировки
        }
    }
    private void swap(int keyLeft, int keyRight, String prefix, Log logs) {
        if (this.viewLogs) {
            logs.add(String.format(
                    "swap [keys: [%d, %d], values: [%d, %d]]",
                    keyLeft,
                    keyRight,
                    this.data[keyLeft],
                    this.data[keyRight]
            ), prefix);
            logs.add(String.format(
                    "result %s",
                    Arrays.toString(this.data)
            ), prefix, false);
        }
        int swap = this.data[keyLeft];
        this.data[keyLeft] = this.data[keyRight];
        this.data[keyRight] = swap;
    }

    // *** Service ***

    private long saveRuntime(long start, String name) {
        long stamp = !this.viewRuntime ? 0 : (new Date()).getTime();
        if (this.viewRuntime) {
            long diff = stamp - start;
            int h = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
            int m = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
            int s = (int) TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
            int ms = (int) TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS);
            String runtime = String
                    .format("%2d-h-%2d-m-%2d.%3d-s", h, m - h * 60, s - m * 60, ms - s * 1000)
                    .replace(' ', '0')
                    .replace('-', ' ');
            this.logsRuntime.add(String.format("%s - %s", runtime, name), "\t\t");
        }
        return stamp;
    }
}

