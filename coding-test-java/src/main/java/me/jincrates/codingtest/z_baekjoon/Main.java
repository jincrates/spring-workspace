package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static long solution(int n, int k, int[] a, int[] p, int minIdx) {
        long answer = 0;

        while (k > 0) {
            if (minIdx > n) {
                break;
            }

            k -= a[minIdx];

            if (k < 0) {
                break;
            }

            answer += p[minIdx];
            minIdx++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 몬스터 수
        int k = sc.nextInt();  // 시루의 초기 체력

        int[] a = new int[n + 1];  // 각 마을 몬스터 파워
        int min = Integer.MAX_VALUE;
        int minIdx = 0;

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();

            if (min > a[i]) {
                min = a[i];
                minIdx = i;
            }

        }

        int[] p = new int[n + 1];  // 각 마을 주민 수
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        System.out.println(solution(n, k, a, p, minIdx));
    }
}
