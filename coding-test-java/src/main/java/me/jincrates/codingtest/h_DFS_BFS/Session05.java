package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//5. 동전교환
public class Session05 {
    static int n, m, answer = Integer.MAX_VALUE;

    public void DFS(int level, int sum, int[] arr) {
        //무한재귀에 빠지지 않도록 처리
        if (sum > m) {
            return;
        }
        if (sum == m) {
            //level: sum을 만드는 동전의 개수
            answer = Math.min(answer, level);
        } else {
            //n가닥으로 뻗어나간다.
            for (int i = 0; i < n; i++) {
                DFS(level + 1, sum + arr[i], arr);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session05 main = new Session05();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        m = sc.nextInt();
        main.DFS(0, 0, arr);
        System.out.println(answer);
    }
}

/*
3
1 2 5
15
*/
