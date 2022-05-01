package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//1. 선택 정렬
public class Session01 {
    public static int[] solution(int[] arr) {
        Arrays.sort(arr);

        return arr;
    }

/*
6
13 5 11 7 23 15
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int x : solution(arr)){
            System.out.print(x + " ");
        };
    }
}