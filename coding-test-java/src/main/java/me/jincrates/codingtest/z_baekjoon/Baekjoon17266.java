package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//17266. 어두운 굴다리
//https://www.acmicpc.net/problem/@@@@
//https://www.notion.so/94jingyu/23-2022-05-15-19-30-1efcbd5153cf411d8e63888b19df4b1a
public class Baekjoon17266 {
    public static boolean search(int[] arr, int n, int height) {
        int start = 0;

        for (int i = 0; i < arr.length; i++) {
            int left = arr[i] - height;
            int right = arr[i] + height;

            if (start < left) {
                return false;
            } else {
                start = right;
            }
        }

        if (n - start > 0) {
            return false;
        }

        return true;
    }

    public static int solution(int n, int m, int[] arr) {
        int answer = 0;

        int lt = 0, rt = n, mid = 0;

        while (lt <= rt) {
            mid = (lt + rt) / 2;

            if (search(arr, n, mid)) {
                // 모든 구간에 빛이 비추는 상황
                answer = mid;
                rt = mid - 1;
            } else {
                // 특정 구간에 빛이 비추지 않는 상황.
                lt = mid + 1;
            }
        }

        return answer;
    }
/*
5
2
2 4
*/
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[m];

        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n, m, arr));
    }
}