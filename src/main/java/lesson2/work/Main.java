package lesson2.work;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void ShakeSort(int[] Array) { // O(N^2)
        for (int i = 0; i < Array.length / 2; i++) {
            for (int j = i; j < Array.length - i - 1; j++) {
                if (Array[j] > Array[j + 1]) {
                    int help = Array[j];
                    Array[j] = Array[j + 1];
                    Array[j + 1] = help;
                }
            }

            for (int j = Array.length - i - 1; j > i; j--) {
                if (Array[j - 1] > Array[j]) {
                    int help = Array[j - 1];
                    Array[j - 1] = Array[j];
                    Array[j] = help;
                }
            }
        }
    }

    final static int[] buf = new int[100000];

    public static void MergeSort(int[] Array) {
        MergeSort(Array, 0, Array.length - 1);
    }

    private static void MergeSort(int[] Array, int left, int right) {
        if (right == left) {
            return;
        }
        int mid = (right + left) / 2;

        MergeSort(Array, left, mid);
        MergeSort(Array, mid + 1, right);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (Array[i] < Array[j]) {
                buf[k] = Array[i];
                i++;
            } else {
                buf[k] = Array[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            buf[k] = Array[i];
            i++;
            k++;
        }

        while (j <= right) {
            buf[k] = Array[j];
            j++;
            k++;
        }

        for (int q = left; q <= right; q++) {
            Array[q] = buf[q];
        }
    }

    public static Integer BinarySearch(int[] Array, int value) {
        int left = 0, right = Array.length - 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (Array[mid] > value) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (Array[left] == value)
            return left;
        if (Array[right] == value)
            return right;
        return null;
    }

    public static void main(String[] args) {
        int N = 100000;
        int[] Array = new int[N];
        int[] Array2 = new int[N];
        for (int i = 0; i < N; i++) {
            Array[i] = (int) (Math.random() * 1000000);
            Array2[i] = Array[i];
        }

//        Date start = new Date();
//        ShakeSort(Array);
//        Date end = new Date();
//        long time1 = end.getTime() - start.getTime();

        // Date start = new Date();
        MergeSort(Array2);
        // Date end = new Date();
        // long time2 = end.getTime() - start.getTime();

        // System.out.printf("time1=%d, time2=%d%n", time1, time2);

        System.out.println(Array[103]);
        System.out.println(BinarySearch(Array2, Array[103]));

    }
}