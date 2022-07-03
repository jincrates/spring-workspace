package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.Scanner;

// 2. 돌다리 건너기
public class Session02 {
    static int[] dy; //dynamic
    public static int solution(int n) {
        //직관적으로 알 수 있음
        dy[1] = 1;
        dy[2] = 2;

        for (int i = 3; i <= n + 1; i++) {
            dy[i] = dy[i - 2] + dy[i - 1];
        }
        return dy[n + 1];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 계단과 다르게 마지막 돌다리를 1번 더 건너야하기 때문
        dy = new int[n + 2];
        System.out.println(solution(n));
    }
}

/*
7

*/
