package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//1. 합이 같은 부분집합(DFS : 아마존 인터뷰)
public class Session01 {
    static String answer = "NO";
    static int n, total = 0;
    boolean flag = false;

    public void DFS(int level, int sum, int[] arr) {
        if (flag) {
            return;
        }
        if (sum > total / 2) {
            return;
        }
        if (level == n) {
            if (total - sum == sum) {
                answer = "YES";
                flag = true;
            }
        } else {
            DFS(level + 1, sum + arr[level], arr);
            DFS(level + 1, sum, arr);
        }
    }

    public static void main(String[] args) throws IOException {
        Session01 main = new Session01();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        main.DFS(0, 0, arr);
        System.out.println(answer);

    }
}

/*
6
1 3 5 6 7 10
*/
