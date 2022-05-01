package me.jincrates.codingtest.z_backjun;

import java.io.*;
import java.util.StringTokenizer;

//1517. 버블 소트
//https://www.acmicpc.net/problem/@@@@
public class Backjun1517 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    answer++;
                }
            }
        }


        // 값 출력
        bw.write(String.valueOf(answer));

        bw.close();
        br.close();
    }

    public void merge(int[] arr, int start, int end) {
        if (start < end) {

            //Divide
            int mid = (start + end) / 2;
            merge(arr, start, mid);
            merge(arr, mid + 1, end);

            //Conquer
            mergeSort(arr, start, mid);
        }
    }

    public void mergeSort(int[] arr, int start, int end) {
        int mid = (start + end) / 2;
        int i = start;
        int j = mid + 1;
        int k = end;

        while (i <= mid && j<= end) {
            if (arr[i] > arr[j]) {

            }
        }
    }
/*
3
3 2 1
*/

}