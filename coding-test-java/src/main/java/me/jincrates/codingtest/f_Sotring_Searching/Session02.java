package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2. 버블 정렬: 맨 뒷자리에 위치시킴
public class Session02 {
    public static int[] solution(int n, int[] arr) {
        for (int i = 0; i < n-1; i++) {
            //j는 반복할 수록 작아짐
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

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

        for (int x : solution(n, arr)) {
            System.out.print(x + " ");
        }
    }
}