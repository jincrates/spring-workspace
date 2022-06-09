package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//8. 순열 추측하기
public class Session08 {
    static int n, f;
    static int[] b, p, ch;
    boolean flag = false;
    int[][] dy = new int[35][35];

    //조합수 구하기
    public int combi(int n, int f) {
        if (dy[n][f] > 0) {
            return dy[n][f];
        }
        if (n == f || f == 0) {
            return 1;
        } else {
            return dy[n][f] = combi(n - 1, f - 1) + combi(n - 1, f);
        }
    }

    public void DFS(int level, int sum) {
        if (flag) {
            return;
        }
        if (level == n) {
            if (sum == f) {
                for (int x : p) {
                    System.out.print(x + " ");
                    flag = true;
                }
            }
        } else {
            //i는 인덱스가 아니라 순열을 만드는 숫자임
            for (int i = 1; i <= n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    p[level] = i;
                    DFS(level + 1, sum + (p[level] * b[level]));
                    ch[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session08 main = new Session08();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        f = sc.nextInt();

        b = new int[n];
        p = new int[n];
        ch = new int[n + 1];
        for (int i = 0; i < n; i++) {
            b[i] = main.combi(n - 1, i);
        }

        main.DFS(0, 0);
    }
}

/*
4 16

*/
