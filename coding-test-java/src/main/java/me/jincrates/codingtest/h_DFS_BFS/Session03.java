package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//3. 최대점수 구하기(DFS)
public class Session03 {
    static int answer = Integer.MIN_VALUE, n, m;

    public void DFS(int level, int sum, int time, int[][] arr) {
        if (time > m) {
            return;
        }

        if (level == n) {
            answer = Math.max(answer, sum); //최대값으로 갱신
        } else {
            //문제를 풀었을 때, 점수와 시간을 더한다.
            DFS(level + 1, sum + arr[level][0], time + arr[level][1], arr);
            DFS(level + 1, sum, time, arr);
        }
    }

    public static void main(String[] args) throws IOException {
        Session03 main = new Session03();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        main.DFS(0, 0, 0, arr);
        System.out.println(answer);
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
