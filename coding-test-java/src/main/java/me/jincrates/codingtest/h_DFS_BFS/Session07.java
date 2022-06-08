package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//7. 조합의 경우수(메모이제이션)
public class Session07 {
    int[][] dy = new int[35][35];  //입력받을 수 있는 범위 33까지인데 넉넉하게 35 잡음

    public int DFS(int n, int r) {
        //메모이제이션 처리
        if(dy[n][r] > 0) {
            return dy[n][r];
        }
        if (n == r || r == 0) {
            return 1;
        } else {
            return dy[n][r] = DFS(n-1, r-1) + DFS(n-1, r);
        }
    }

    public static void main(String[] args) throws IOException {
        Session07 main = new Session07();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();

        System.out.println(main.DFS(n, r));
    }
}

/*
5 3

33 19
*/
