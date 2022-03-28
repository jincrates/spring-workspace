package me.jincrates.codingtest.d_HashMap_TreeSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//2. 아나그램(HashMap)
public class Session02 {
    public static String solution(String str1, String str2) {
        String answer = "NO";

        HashMap<Character, Integer> map1 = new HashMap<>();

        for (char x : str1.toCharArray()) {
            map1.put(x, map1.getOrDefault(x, 0) + 1);
        }

        HashMap<Character, Integer> map2 = new HashMap<>();

        for (char x : str2.toCharArray()) {
            map2.put(x, map2.getOrDefault(x, 0) + 1);
        }

        //맵 비교
        if (map1.equals(map2)) {
            answer = "YES";
        }

        return answer;
    }
/*
AbaAeCe
baeeACA

abaCC
Caaab

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        System.out.println(solution(str1, str2));
    }
}