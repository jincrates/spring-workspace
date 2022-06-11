package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//9. 조합 구하기
public class Session09 {
    static int[] combi;
    static int n, m;
    public void DFS(int level, int start) {
        if(level == m) {
            for (int x : combi) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            //조합은 외워버리는 것이 좋다.
            for (int i = start; i <= n; i++) {
                combi[level] = i;
                DFS(level + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session09 main = new Session09();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        combi = new int[m];
        main.DFS(0, 1);
    }
}

/*
4 2

*/
