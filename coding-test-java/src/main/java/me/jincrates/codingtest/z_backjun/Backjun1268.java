package me.jincrates.codingtest.z_backjun;

import java.util.*;

//1268. 임시 반장 정하기
//https://www.acmicpc.net/problem/@@@@
public class Backjun1268 {

    public static int solution(int n, int[][] students) {
        int answer = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 5; k++) {
                    if (students[i][k] == students[j][k]) {
                        System.out.println("students[" + j + "][" + k + "] = " + students[j][k] + ", cnt = " + cnt);
                        System.out.println("students[" + i + "][" + k + "] = " + students[i][k] + ", cnt = " + cnt);
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt > max) {
                max = cnt;
                answer = i + 1;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] students = new int[n][5];  //

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                students[i][j] = sc.nextInt();
            }
        }
        
        System.out.println(solution(n, students));
    }
}
