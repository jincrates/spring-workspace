package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// 6. 친구인가? (Union & Find)
// 서로소
public class Session06 {
    static int[] unf;

    // 학생의 집합 인덱스 번호를 리턴(같은 집합인지 보기 위해)
    public static int find(int v) {
        if (v == unf[v]) {
            return v;
        } else {
            // return find(unf[v]);
            // 재귀를 줄이고 경로를 압축** (그래프가 바뀜)
            return unf[v] = find(unf[v]);
        }
    }

    // a와 b를 하나의 집합으로 union
    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;  // 배열 초기화
        }

        for (int i = 1; i <= m; i++) {
            union(sc.nextInt(), sc.nextInt());
        }

        int a = sc.nextInt();
        int b = sc.nextInt();
        int fa = find(a);
        int fb = find(b);
        
        if (fa == fb) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}

/*
9 7
1 2
2 3
3 4
1 5
6 7
7 8
8 9
3 8

*/