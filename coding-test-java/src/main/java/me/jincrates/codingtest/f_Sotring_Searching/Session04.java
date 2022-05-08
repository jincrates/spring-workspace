package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//4. Least Recently Used
public class Session04 {
    public static int[] solution(int n, int m, int[] arr) {
        System.out.println(n);
        System.out.println(m);

        return arr;
    }

/*
5 9
1 2 3 2 6 2 3 5 7
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int x : solution(n, m, arr)) {
            System.out.print(x + " ");
        }
    }
}