package me.jincrates.codingtest.z_backjun;

import java.io.*;
import java.util.Arrays;

//1337. 올바른 배열(못풀고 구글링)
//https://www.acmicpc.net/problem/@@@@
public class Backjun1337 {

    public static int solution(int n, int[] arr) {
        int answer = 0;
        int lt = 0, cnt = 0;

        //배열 오름차순 정렬
        Arrays.sort(arr);

        for (int rt = 0; rt < n; rt++) {
            cnt++;

            while (arr[rt] - arr[lt] > 4) {
                cnt--;
                lt++;
            }

            answer = Math.max(answer, cnt);
        }

        if (answer > 5) {
            answer = 5;
        }

        return 5 - answer;
    }
/*
ex1
3
5
6
7
=> 2

ex2
6
5
7
9
8492
8493
192398
=> 2
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(n, arr));
    }
}