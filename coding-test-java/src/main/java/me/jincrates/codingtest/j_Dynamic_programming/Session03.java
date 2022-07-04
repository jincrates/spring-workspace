package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.Scanner;

// 3. 최대 부분 증가수열(LIS)
public class Session03 {
    static int[] dy;

    public static int solution(int[] arr) {
        int answer = 0;
        dy = new int[arr.length];  // dy[i] => i번째 숫자를 마지막항으로 하는 최대 증가 수열의 길이

        // 직관적인 값을 먼저 할당
        dy[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                // 앞의 항들 중에서
                if (arr[j] < arr[i] && dy[j] > max) {
                    max = dy[j];
                }
            }

            dy[i] = max + 1;
            answer = Math.max(answer, dy[i]);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(arr));
    }
}

/*
8
5 3 7 8 6 2 9 4

5(3) 7 8 9 -> 4

*/
