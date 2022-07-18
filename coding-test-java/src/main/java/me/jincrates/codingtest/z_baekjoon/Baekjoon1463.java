package me.jincrates.codingtest.z_baekjoon;

import java.util.Scanner;

//1463. 1로 만들기
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1463 {
    static int[] dy;

    public static int solution(int x) {
        // 2을 처리하는 방법
        // 2를 3으로 나눈다 -> 안덜어짐
        // 2를 2로 나눈다. -> 1번
        // 2에서 1을 뺀다. 1-> 1번
        /*
dy[1]    dy[2]    dy[3]   dy[4]  dy[5]    dy[6]   dy[7]    dy[8]   dy[9]   dy[10]
 1        2        3       4      5        6        7       8       9        10
 1        1        1       2      3        2        3       3       2         3

        */
        for (int i = 2; i <= x; i++) {
            dy[i] = dy[i - 1] + 1;

            if (i % 3 == 0) {
                dy[i] = Math.min(dy[i], dy[i / 3] + 1);
            }

            if (i % 2 == 0) {
                dy[i] = Math.min(dy[i], dy[i / 2] + 1);
            }

            System.out.println("dy[" + i + "] = " + dy[i]);
        }

        return dy[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dy = new int[n + 1];

        System.out.println(solution(n));
    }
}

/*
10   =>  3

 */