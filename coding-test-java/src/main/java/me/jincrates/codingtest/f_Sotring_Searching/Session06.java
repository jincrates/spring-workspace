package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//6. 장난꾸러기
public class Session06 {
    public static String solution(int n, int[] arr) {
        String answer = "";

        //배열 복사(정렬X)
        int[] temp = arr.clone();

        //정렬(O)
        Arrays.sort(temp);

        //두 배열 비교
        for (int i = 0; i < n; i++) {
            if (arr[i] != temp[i]) {
                answer += (i + 1) + " ";
            }
        }

        return answer;
    }

/*
9
120 125 152 130 135 135 143 127 160
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, arr));
    }
}