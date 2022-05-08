package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//4158. CD(투포인터)
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon4158 {

/*
4 3
1
2
3
4
1
2
4
0 0
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] line1 = br.readLine().split(" ");
            int n = Integer.parseInt(line1[0]);
            int m = Integer.parseInt(line1[1]);

            if (n == 0 && m == 0) {
                break;
            }

            int[] arr1 = new int[n];
            int[] arr2 = new int[m];

            for (int i = 0; i < n; i++) {
                arr1[i] = Integer.parseInt(br.readLine());
            }

            for (int i = 0; i < m; i++) {
                arr2[i] = Integer.parseInt(br.readLine());
            }

            int answer = 0;
            int p1 = 0, p2 = 0;

            while (true) {
                if (p1 == arr1.length || p2 == arr2.length) {
                    break;
                }
                if (arr1[p1] == arr2[p2]) {
                    answer++;
                    p1++;
                    p2++;
                } else if (arr1[p1] > arr2[p2]) {
                    p2++;
                } else {
                    p1++;
                }
            }

            System.out.println(answer);
        }
    }
}