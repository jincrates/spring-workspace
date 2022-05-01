package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//3. 삽입 정렬: j 앞을 정렬시킴
public class Session03 {
    public static int[] solution(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            int temp = arr[i], j;
            for (j = i-1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = temp;
        }
        return arr;
    }

/*
6
11 7 5 6 10 9
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