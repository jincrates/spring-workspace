package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//11. 임시반장 정하기
public class Session11 {
    public static int solution(int n, int[][] arr) {
        int answer = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++){
            int cnt = 0;

            for (int j = 1; j <= n; j++) {

                //1~5학년
                for (int k = 1; k <= 5; k++) {
                    if(arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;  //2번 학생이 4번 학생과 3학년 4학년 두번 같은 반을 했어도 카운트는 1번만 해야하기 때문에 break문으로 빠져나온다.
                    }
                }
            }
            if (cnt > max) {
                max = cnt;
                answer = i;
            }
        }

        return answer;
    }

/*
5
2 3 1 7 3
4 1 9 6 8
5 5 2 4 4
6 5 2 6 7
8 4 2 2 2

 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n + 1][6];

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= 5; j++) {  //5학년까지
                arr[i][j] = kb.nextInt();
            };
        }

        System.out.print(solution(n, arr));
    }
}
