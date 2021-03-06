package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//3. 최대 매출
public class Session03 {
    public static int solution(int n, int cnt, int[] arr) {
        int answer = 0;
        int sum = 0;

        //만들 수 있는 모든 경우의 수를 가져온다.
        //방법1. 시간복잡도: (n-cnt) * cnt
        /*
        int idx;
        for (int i = 0; i < n - cnt; i++) {
            idx = 0;
            sum = 0;

            while (idx < cnt) {
                sum += arr[i + idx++];
            }

            if (sum > answer) {
                answer = sum;
            }
        }
        */

        //방법2. sliding windows : 반복문 1개로 해결
        //초기값(answer) 셋팅
        for (int i = 0; i < cnt; i++) {
            sum += arr[i];
        }

        answer = sum;

        for (int i = cnt; i < n; i++) {
            sum += (arr[i] - arr[i - cnt]);

            answer = Math.max(answer, sum);
        }

        return answer;
    }
/*
10 3
12 15 11 20 25 10 20 19 13 15

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int cnt = Integer.parseInt(line1[1]);
        int[] arr = new int[n];

        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(n, cnt, arr));
    }
}
