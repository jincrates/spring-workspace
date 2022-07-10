package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// 6. 최대점수 구하기(냅색 알고리즘)
public class Session06 {
    static int[] dy;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        dy = new int[m + 1];

        for (int i = 0; i < n; i++) {
            int ps = sc.nextInt();
            int pt = sc.nextInt();

            for (int j = m; j >= pt; j--) {
                dy[j] = Math.max(dy[j], dy[j - pt] + ps);
            }
        }

        System.out.println(dy[m]);
    }
}

/*
5 20
10 5
25 12
15 8
6 3
7 4



*/
