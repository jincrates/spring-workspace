package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//10. 마구간 정하기(결정 알고리즘)
public class Session10 {
    //결정 알고리즘은 count함수를 만드는 것이 능력
    public static int count(int[] arr, int dist) {
        int cnt = 1;
        int ep = arr[0];  //제일 왼쪽 좌표

        for (int i = 1, max = arr.length; i < max; i++) {
            if (arr[i] - ep >= dist) {
                cnt++;
                ep = arr[i];
            }
        }

        return cnt;
    }

    public static int solution(int n, int c, int[] arr) {
        int answer = 0;

        //오름차순 정렬
        Arrays.sort(arr);

        int lt = 1;
        int rt = arr[n - 1];

        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (count(arr, mid) >= c) {
                answer = mid;
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }

        return answer;
    }

/*
5 3
1 2 8 4 9
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, c, arr));
    }
}