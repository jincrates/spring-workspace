package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//21921. 블로그
//https://www.acmicpc.net/problem/@@@@
public class Backjun21921 {

    public static void solution(int n, int x, int[] arr) {
        long answer = 0, sum = 0, cnt = 1;

        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }

        //초기값 셋팅
        answer = sum;

        for (int i = x; i < n; i++) {
            sum += (arr[i] - arr[i-x]);

            if (answer == sum) {
                cnt++;
            } else if (answer < sum) {
                cnt = 1;
            }

            answer = Math.max(answer, sum);
        }

        //방문자가 없으면 SAD 리턴
        if (answer == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(answer);
            System.out.println(cnt);
        }
    }
/*
5 2
1 4 2 5 2

7 5
1 1 1 1 1 5 1

5 3
0 0 0 0 0
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int x = Integer.parseInt(line1[1]);
        int[] arr = new int[n];

        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        solution(n, x, arr);
    }
}