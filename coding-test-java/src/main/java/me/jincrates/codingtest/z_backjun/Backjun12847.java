package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//12847. 꿀 아르바이트
//리턴 타입을 int에서 long으로 변경
//https://www.acmicpc.net/problem/@@@@
public class Backjun12847 {

    public static long solution(int n, int m, int[] arr) {
        long answer = 0, sum = 0;

        //준수가 일할 수 있는 창문을 이동시키자
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }

        //초기값 셋팅: 첫날부터 일했을때 받을 수 있는 최대 급여
        answer = sum;

        //창문 이동
        for (int i = m; i < n; i++) {
            sum += (arr[i] - arr[i-m]);  //앞서가는 창문을 올려주고 뒷따라 가는 창문은 빼줌
            answer = Math.max(sum, answer); //초기값과 비교하여 가장 큰수 출력
        }

        return answer;
    }
/*
5 3
10 20 30 20 10
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[] arr = new int[n];

        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(n, m, arr));
    }
}