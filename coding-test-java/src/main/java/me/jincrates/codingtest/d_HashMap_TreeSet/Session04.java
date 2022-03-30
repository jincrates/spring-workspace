package me.jincrates.codingtest.d_HashMap_TreeSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//4. 모든 아나그램 찾기(Hash, sliding window : 시간복잡도 O(n))
public class Session04 {
    public static int solution(String s, String t) {
        int answer = 0;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        HashMap<Character, Integer> sHm = new HashMap<>();
        HashMap<Character, Integer> tHm = new HashMap<>();

        for (int i = 0; i < tArr.length; i++) {
            tHm.put(tArr[i], tHm.getOrDefault(tArr[i], 0) + 1);
        }
        for (int i = 0; i < tArr.length - 1; i++) {
            sHm.put(sArr[i], sHm.getOrDefault(sArr[i], 0) + 1);
        }

        int lt = 0;
        for (int rt = tArr.length - 1, max = sArr.length; rt < max; rt++) {
            sHm.put(sArr[rt], sHm.getOrDefault(sArr[rt], 0) + 1);

            if (tHm.equals(sHm)) {
                answer++;
            }

            sHm.put(sArr[lt], sHm.get(sArr[lt]) - 1);

            if (sHm.get(sArr[lt]) == 0) {
                sHm.remove(sArr[lt]);
            }
            lt++;
        }
        
        return answer;
    }
/*
bacaAacba
abc

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solution(s, t));
    }
}