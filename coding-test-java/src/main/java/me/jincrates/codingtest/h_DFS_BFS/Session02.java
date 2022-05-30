package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//2. 바둑이 승차(DFS)
public class Session02 {
    static int answer = Integer.MIN_VALUE, c, n;

    public void DFS(int level, int sum, int[] arr) {
        if (sum > c) {
            return;
        }

        if (level == n) {
            answer = Math.max(answer, sum); //최대값으로 갱신
        } else {
            DFS(level + 1, sum + arr[level], arr);
            DFS(level + 1, sum, arr);
        }
    }

    public static void main(String[] args) throws IOException {
        Session02 main = new Session02();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();  //트럭 한계 무게
        n = sc.nextInt();  //바둑이 수
        int[] arr = new int[n];

        //바둑이 무게
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        main.DFS(0, 0, arr);
        System.out.println(answer);

    }
}

/*
259 5
81
58
42
33
61
*/
