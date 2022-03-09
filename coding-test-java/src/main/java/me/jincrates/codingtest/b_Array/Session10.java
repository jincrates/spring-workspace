package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//10. 봉우리
public class Session10 {
    public static int solution(int n, int[][] arr) {
        int answer = 0;
        
        for (int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++) {
                //System.out.println("arr[" + i + "][" + j + "] = " + arr[i][j]);
                //System.out.print(arr[i][j] + " ");
                if (arr[i - 1][j] < arr[i][j] && arr[i][j] > arr[i + 1][j]
                && arr[i][j - 1] < arr[i][j] && arr[i][j] > arr[i][j + 1]) {
                    answer++;
                }
            }
            //System.out.println(" ");
        }
        
        return answer;
    }

/*
5
5 3 7 2 3
3 7 1 6 1
7 2 5 3 4
4 3 6 4 1
8 7 3 5 2
 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n + 2][n + 2];

        for (int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                arr[i][j] = kb.nextInt();
            };
        }

        System.out.print(solution(n, arr));
    }
}
