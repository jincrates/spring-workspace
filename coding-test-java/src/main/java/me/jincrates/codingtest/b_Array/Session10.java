package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//10. 봉우리
public class Session10 {

    public static int solution(int n, int[][] arr) {
        int answer = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k]; // 행좌표
                    int ny = j + dy[k]; // 열좌표

                    //좌표에 따른 값이 기준점보다 큰게 있는지 검증
                    if (nx >=0 && nx < n && ny >= 0 && ny < n) {
                        if (arr[nx][ny] >= arr[i][j]) {
                            flag = false;   // 봉우리가 아니다.
                            break;
                        }
                    }
                }

                if (flag) {
                    answer++;
                }
                /*
                if (arr[i - 1][j] < arr[i][j] && arr[i][j] > arr[i + 1][j]
                && arr[i][j - 1] < arr[i][j] && arr[i][j] > arr[i][j + 1]) {
                    answer++;
                }
                */
            }
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
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            };
        }

        System.out.print(solution(n, arr));
    }
}
