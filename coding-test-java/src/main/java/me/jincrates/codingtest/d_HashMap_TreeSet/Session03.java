package me.jincrates.codingtest.d_HashMap_TreeSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//3. 매출액의 종류(Hash, sliding window)
public class Session03 {
    public static ArrayList<Integer> solution(int n, int k, int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < k - 1; i++) {
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
        }

        int lt = 0;
        for (int rt = k - 1; rt < n; rt++) {
            hm.put(arr[rt], hm.getOrDefault(arr[rt], 0) + 1);
            answer.add(hm.size());  //키의 종류

            hm.put(arr[lt], hm.get(arr[lt]) - 1);
            if (hm.get(arr[lt]) == 0) {
                hm.remove(arr[lt]);
            }
            lt++;
        }

        return answer;
    }
/*
7 4
20 12 20 10 23 17 10

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int k = Integer.parseInt(line1[1]);

        String[] line2 = br.readLine().split(" ");
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        for (int result : solution(n, k, arr)) {
            System.out.print(result + " ");
        }
    }
}