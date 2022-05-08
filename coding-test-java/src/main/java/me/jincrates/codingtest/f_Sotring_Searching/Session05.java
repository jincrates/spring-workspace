package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//5. 중복확인
public class Session05 {
    public static String solution(int n, int[] arr) {
        String answer = "U";

        //해시맵으로 풀면 시간복잡도가 더 낮다.
        /*
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int key = arr[i];
            map.put(key, map.getOrDefault(key, 0) + 1);

            if (map.get(key) > 1) {
                answer = "D";
                break;
            }
        }
        */
        //정렬로도 풀어보자: 시간복잡도가  nlogn
        //오름차순 정렬
        Arrays.sort(arr);

        for (int i = 0; i < n-1; i++) {
            if (arr[i] == arr[i+1]) {
                answer = "D";
                break;
            }
        }

        return answer;
    }

/*
8
20 25 52 30 39 33 43 33
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