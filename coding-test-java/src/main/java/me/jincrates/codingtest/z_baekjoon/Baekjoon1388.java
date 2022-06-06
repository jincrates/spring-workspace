package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//1388. 바닥 장식
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1388 {

    public static void main(String[] args) throws IOException {
        Baekjoon1388 main = new Baekjoon1388();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] arr = new char[n][m];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '-') {
                    answer++;

                    while (j < m && arr[i][j] == '-') {
                        j++;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == '|') {
                    answer++;
                    while (j < n && arr[j][i] == '|') {
                        j++;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
/*
4 4
----
----
----
----

6 9
-||--||--
--||--||-
|--||--||
||--||--|
-||--||--
--||--||-

*/