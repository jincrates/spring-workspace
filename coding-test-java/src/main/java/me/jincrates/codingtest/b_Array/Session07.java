package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//7. 점수 계산
public class Session07 {
    public static int solution(int n, int[] arr) {
        int answer = 0;
        int score = 0;
        for(int num : arr) {
            if(num == 1) {
                score++;
            } else {
                score = 0;
            }
            answer += score;
        }

        return answer;
    }

/*
10
1 0 1 1 1 0 0 1 1 0
 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(solution(n, arr));
    }
}
