package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.Scanner;

// 1. 계단오르기
public class Session01 {
    static int[] dy; //dynamic
    public static int solution(int n) {
        // 직관적으로 구할 수 있음
        dy[1] = 1;
        dy[2] = 2;

        for (int i = 3; i <= n; i++) {
            dy[i] = dy[i-2] + dy[i-1];
        }

        return dy[n];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dy = new int[n+1];

        System.out.println(solution(n));
    }
}

/*
7

*/
