package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//9. 뮤직비디오(결정 알고리즘)
public class Session09 {
    public static int count(int[] arr, int capacity) {
        //cnt: DVD장 수, 한장의 DVD에 누적된 용량
        int cnt = 1, sum = 0;

        for (int x : arr) {
            if (sum + x > capacity) {
                cnt++;
                sum = x;
            } else {
                sum += x;
            }
        }

        return cnt;
    }

    public static int solution(int n, int m, int[] arr) {
        int answer = 0;

        //DVD 최소 용량은 9, 최대 용량은 총합 45
        int lt = Arrays.stream(arr).max().getAsInt();  //max
        int rt = Arrays.stream(arr).sum();

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (count(arr, mid) <= m)  {
                answer = mid;

                //만족하면 그 다음 만족하는 수가 있는지 탐색
                rt = mid - 1;
            } else {
                //만족하지 못하면 lt를 좁혀줌
                lt = mid + 1;
            }
        }

        return answer;
    }

/*
9 3
1 2 3 4 5 6 7 8 9
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