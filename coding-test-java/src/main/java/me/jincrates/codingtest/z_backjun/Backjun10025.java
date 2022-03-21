package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

//10025. 게으른 백곰
//https://www.acmicpc.net/problem/@@@@
public class Backjun10025 {

    public static int solution(int k, int[] arr) {
        int answer = 0, lt = 0, sum = 0;
        int move = k * 2 + 1; //좌우 모두 이동 가능하기 때문
        //xi = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ....
        //gi = _ 5 2 _ _ _ _ 4 _ _ __ __ __ __ __ 10 ...

        for (int rt = 0; rt < arr.length; rt++) {
            sum += arr[rt];
            answer = Math.max(answer, sum);

            //창문이동
            if (rt >= move) {
                sum -= arr[lt];
                lt++;
            }
        }

        return answer;
    }
/*
4 3
4 7
10 15
2 2
5 1
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int k = Integer.parseInt(line1[1]);
        int[] arr = new int[1000001];
        int g = 0, x = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            String[] line2 = br.readLine().split(" ");
            g = Integer.parseInt(line2[0]);
            x = Integer.parseInt(line2[1]);
            arr[x] = g;  // 해당 좌표에 얼음 배치
        }

        System.out.println(solution(k, arr));
    }
}