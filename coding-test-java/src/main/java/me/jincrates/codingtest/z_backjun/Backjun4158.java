package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//4158. CD(투포인터)
//https://www.acmicpc.net/problem/@@@@
public class Backjun4158 {

    public static int solution(int[] arr1, int[] arr2) {
        int answer = 0;

        int p1 = 0, p2 = 0;

        while (p1 < arr1.length && p2 < arr2.length) {
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

        return answer;
    }
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

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for (int i = 0, max = n + m; i < max; i++) {
            int cd = Integer.parseInt(br.readLine());

            if (cd > 0) {
                if (i < n) {
                    arr1[i] = cd;
                } else {
                    arr2[i - n] = cd;
                }
            }
        }

        System.out.println(solution(arr1, arr2));
    }
}