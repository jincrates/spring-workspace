package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//8. 이분검색
public class Session08 {
    public static int solution(int n, int m, int[] arr) {
        int answer = 0;

        //이분검색은 정렬이 되어야 한다.
        //검색 범위를 줄여준다.
        Arrays.sort(arr);

        int lt = 0, rt = n-1;

        //while문 사용
        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            //m을 찾았을때 반복문 빠져나옴
            if (arr[mid] == m) {
                answer = mid + 1;
                break;
            }

            //여기가 이분검색의 핵심
            if (arr[mid] > m) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }

        return answer;
    }

/*
8 32
23 87 65 12 57 32 99 81
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, m, arr));
    }
}