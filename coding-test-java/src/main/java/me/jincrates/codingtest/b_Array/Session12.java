package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//12. 멘토링
public class Session12 {
    public static int solution(int student, int exam, int[][] arr) {
        int answer = 0;
        //멘토 멘티가 짝을 이룰 수 있는 전체 경우의 수 4*4
        for (int i = 1; i <= student; i++) {
            for (int j = 1; j <= student; j++) {
                int cnt = 0;

                //등수 검증
                for (int k = 0; k < exam; k++) {
                    int pi = 0, pj = 0;

                    for (int s = 0; s < student; s++) {
                        if (arr[k][s] == i) {
                            pi = s;
                        }
                        if (arr[k][s] == j) {
                            pj = s;
                        }
                    }

                    if (pi < pj) {
                        cnt++;
                    }
                }
                if (cnt == exam) {
                    answer++;
                }
            }
        }
        
        return answer;
    }

/*
4 3
3 4 1 2
4 3 2 1
3 1 4 2

 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int student = kb.nextInt();
        int exam = kb.nextInt();
        int[][] arr = new int[exam][student];

        for (int i = 0; i < exam; i++) {
            for(int j = 0; j < student; j++) {
                arr[i][j] = kb.nextInt();
            };
        }

        System.out.print(solution(student, exam, arr));
    }
}
