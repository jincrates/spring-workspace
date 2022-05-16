package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//1654. 랜선 자르기
//https://www.acmicpc.net/problem/@@@@
//https://www.notion.so/94jingyu/23-2022-05-15-19-30-1efcbd5153cf411d8e63888b19df4b1a
public class Baekjoon1654 {
    public static int count(int[] arr, long length) {
        int cnt = 0;

        for(long x : arr) {
            //왜 나눌 생각을 못했지
            cnt += x / length;

//            while (x >= length) {
//                x -= length;
//                cnt++;
//            }
        }

        return cnt;
    }

    public static long solution(int k, long n, int[] arr) {
        long answer = 0L;

        //오름차순 정렬
        Arrays.sort(arr);

        //최소 랜선의 길이
        int lt = 1;
        int rt = Arrays.stream(arr).min().getAsInt();

        while (lt <= rt) {
            int mid = (lt + rt) / 2;  //229
            //System.out.println("lt = " + lt + ", rt = " + rt + ", mid = " + mid + ", count = " + count(arr, mid));
            
            if (count(arr, mid) >= n) {
                //n이 11인 값 중에서 최대값
                answer = mid;

                //만족하지 못하면 lt를 좁혀줌
                lt = mid + 1;
            } else {
                //만족하면 그 다음 만족하는 수가 있는지 탐색
                rt = mid - 1;
            }
        }

        return rt;
    }
/*
4 11
802
743
457
539
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        long n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(k, n, arr));
    }
}