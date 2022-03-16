package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//4. 연속 부분수열(복합적 문제)
public class Session04 {
    public static int solution(int n, int m, int[] arr) {
        int answer = 0, sum = 0, lt = 0;

        for (int rt = 0; rt < n; rt++) {
            sum += arr[rt];

            if(sum == m) {
                answer++;
            }

            while(sum >= m) {
                sum -= arr[lt++];

                if (sum == m) {
                    answer++;
                }
            }
        }

        return answer;
    }
/*
8 6
1 2 1 3 1 1 1 2

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[] arr = new int[n];

        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(n, m, arr));
    }
}
