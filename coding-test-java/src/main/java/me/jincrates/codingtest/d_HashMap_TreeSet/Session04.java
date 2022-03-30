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
        HashMap<Character, Integer> sHm = new HashMap<>();
        HashMap<Character, Integer> tHm = new HashMap<>();

        //비교군 셋팅
        for (char x : t.toCharArray()) {
            tHm.put(x, tHm.getOrDefault(x, 0) + 1);
        }

        //초기값 설정
        for (int i = 0; i < t.length() - 1; i++) {
            sHm.put(s.charAt(i), sHm.getOrDefault(s.charAt(i), 0) + 1);
        }

        //슬라이딩 윈도우
        int lt = 0;
        for (int rt = t.length() - 1, max = s.length(); rt < max; rt++) {
            sHm.put(s.charAt(rt), sHm.getOrDefault(s.charAt(rt), 0) + 1);

            if (tHm.equals(sHm)) {
                answer++;
            }

            sHm.put(s.charAt(lt), sHm.get(s.charAt(lt)) - 1);

            if (sHm.get(s.charAt(lt)) == 0) {
                sHm.remove(s.charAt(lt));
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