package lesson1.work;

import java.util.Date;

public class Main {

    public static int Cubs(int K, int N) {
        return Cubs(K, N, 1);
    }

    public static int Cubs(int K, int N, int H) { // O(N^K)
        if (H == K + 1) {
            return 1;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) { // O(N)
            res += Cubs(K, N, H + 1);
        }
        return res;
    }

    public static int Sum(int N) { // O(1) + O(N) * O(1) + O(1) = O(N)
        int res = 0; // O(1)
        for (int i = 1; i <= N; i++) { // O(N)
            res += i; // O(1)
        }
        return res; // O(1)
    }

    public static long F1(int N) {
        if (N <= 2) {
            return 1;
        }
        return F1(N - 1) + F1(N - 2);
    }

    public static long F2(int N) {
        if (N <= 2) {
            return 1;
        }
        long[] F = new long[N + 1];
        F[1] = 1;
        F[2] = 1;

        for (int i = 3; i <= N; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }

        return F[N];
    }

    public static void Simple(int N) { // O(N * sqrt(N))
        for (int num = 2; num <= N; num++) { // O(N)
            boolean simple = true; // O(1)
            for (int i = 2; i * i <= num; i++) { // O(sqrt(N))
                if (num % i == 0) { // O(1)
                    simple = false; // O(1)
                    break; // O(1)
                }
            }
            if (simple) { // O(1)
                System.out.printf("%d ", num); // O(1)
            }
        }
    }

    public static void test() {
        for (int N = 10; N <= 50; N++) {
            Date start = new Date();
            F1(N);
            Date end = new Date();
            long time1 = end.getTime() - start.getTime();

            start = new Date();
            F2(N);
            end = new Date();
            long time2 = end.getTime() - start.getTime();

            System.out.printf("N = %d, time1 = %d, time2 = %d%n", N, time1, time2);
        }
    }

    public static void main(String[] args) {
        // Simple(100);
        // System.out.println(Cubs(4, 6));
        // System.out.println(F2(10));
        test();
    }
}