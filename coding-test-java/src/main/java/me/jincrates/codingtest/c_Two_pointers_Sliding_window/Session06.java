package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//6. 최대 길이 연속부분수열
public class Session06 {
    public static int solution(int n, int k, int[] arr) {
        int answer = 0, lt = 0, cnt = 0;  // cnt: 0을 1로 바꾼 횟수

        for (int rt = 0; rt < n; rt++) {

            if (arr[rt] == 0) {
                cnt++;

                while(cnt > k) {
                    if(arr[lt] == 0) {
                        cnt--;
                    }
                    lt++;
                }
            }
            answer = Math.max(answer, rt - lt + 1);
        }

        return answer;
    }
/*
14 2
1 1 0 0 1 1 0 1 1 0 1 1 0 1

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int k = Integer.parseInt(line1[1]);

        String[] line2 = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(n, k, arr));
    }
}
