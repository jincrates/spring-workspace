package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//6. 순열 구하기
public class Session06 {
    static int[] pm, ch, arr;
    static int n, m;

    public void DFS(int level) {
        //순열이 하나 만들어진거
        if (level == m) {
            for (int x : pm) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for (int i = 0; i < n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    pm[level] = arr[i];
                    DFS(level + 1);
                    ch[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session06 main = new Session06();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        ch = new int[n];
        pm = new int[m];

        main.DFS(0);
    }
}

/*
3 2
3 6 9


*/
