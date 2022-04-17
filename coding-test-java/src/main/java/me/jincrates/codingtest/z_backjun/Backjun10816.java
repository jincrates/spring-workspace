package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//10816. 숫자 카드2
//https://www.acmicpc.net/problem/@@@@
public class Backjun10816 {

    public static String solution(int[] arr1, int[] arr2) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> hm = new HashMap<>();

        //1. 가지고 있는 카드를 HashMap에 셋팅
        for (int i = 0, max = arr1.length; i < max; i++) {
            hm.put(arr1[i], hm.getOrDefault(arr1[i], 0) + 1);
        }

        //2. 비교할 숫자
        for (int i = 0, max = arr2.length; i < max; i++) {
            sb.append(hm.getOrDefault(arr2[i], 0)).append(" ");
        }
        answer = sb.toString();

        return answer;
    }
/*
10
6 3 2 10 10 10 -10 -10 7 3
8
10 9 -5 2 3 4 5 -10
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        String[] line1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(line1[i]);
        }

        int m = Integer.parseInt(br.readLine());
        int[] arr2 = new int[m];
        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(arr1, arr2));
    }
}