package me.jincrates.codingtest.z_baekjoon;

import java.util.Scanner;

//2562. 최댓값
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon2562 {

    public static void solution(int[] arr) {
        int answer = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 9; i++) {
            if (arr[i] > max) {
                max = arr[i];
                answer = i + 1;
            }
        }

        System.out.println(max);
        System.out.println(answer);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();            
        }
        
        solution(arr);
    }
}
