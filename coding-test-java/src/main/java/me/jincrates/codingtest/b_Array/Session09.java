package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//9. 격자판 최대합
public class Session09 {
    public static int solution(int n, int[][] arr) {
        int answer = 0;
        int sum = 0;
        int max = 0;

        //가로줄 검증
        for (int i = 0; i < n; i++) {
            sum = 0;    
            
            for(int j = 0; j < n; j++) {
                sum += arr[i][j];
            }

            if (max < sum) {
                max = sum;
            }
        }

        //세로줄 검증
        for (int i = 0; i < n; i++) {
            sum = 0;

            for(int j = 0; j < n; j++) {
                sum += arr[j][i];
            }

            if (max < sum) {
                max = sum;
            }
        }

        //좌대각선 검증
        sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i][i];
            if (max < sum) {
                max = sum;
            }
        }

        //우대각선 검증  i0 5  i1 j4
        sum = 0;
        for (int i = 0; i < n; i++) {
            //System.out.println("i = " + i + ", n - i = " + (n - i));
            sum += arr[i][(n - 1) - i];
            if (max < sum) {
                max = sum;
            }
        }

        answer = max;

        return answer;
    }

/*
5
10 13 10 12 15
12 39 30 23 11
11 25 50 53 15
19 27 29 37 27
19 13 30 13 19
 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            }
        }

        System.out.print(solution(n, arr));
    }
}
