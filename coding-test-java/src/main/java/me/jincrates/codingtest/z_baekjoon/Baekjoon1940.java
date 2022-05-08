package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1940. 주몽
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1940 {

    public static int solution(int n, int m, Integer[] arr) {
        int answer = 0;

        //오름차순 정렬
        Arrays.sort(arr);

        //주어진 배열을 가지고 만들 수 있는 수(중복X)
        int start = 0;
        int end = n - 1;

        while (start < end) {
            if (arr[start] + arr[end] == m) {
                answer++;
                start++;
                end--;
            } else if (arr[start] + arr[end] < m) {
                start++;
            } else {
                end--;
            }
        }

        return answer;
    }
/*
6
9
2 7 4 1 5 3
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];

        String[] line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        System.out.println(solution(n, m, arr));
    }
}