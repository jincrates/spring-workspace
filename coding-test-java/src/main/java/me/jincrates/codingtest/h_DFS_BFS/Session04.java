package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//4. 중복순열 구하기
public class Session04 {
    static int[] pm;
    static int n, m;

    public void DFS(int level) {
        if (level == m) {
            for (int x : pm) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                pm[level] = i;
                DFS(level + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session04 main = new Session04();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pm = new int[m];

        main.DFS(0);
    }
}

/*
3 2

*/
