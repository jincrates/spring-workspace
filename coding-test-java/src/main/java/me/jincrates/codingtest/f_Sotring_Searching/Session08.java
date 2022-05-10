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

        //오른차순 정렬
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (m == arr[i]) {
                answer = i+1;
                break;
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