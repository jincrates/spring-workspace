package me.jincrates.codingtest.d_HashMap_TreeSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//1. 학급회장(해쉬)
public class Session01 {
    public static char solution(int n, String s) {
        char answer = ' ';

        HashMap<Character, Integer> map = new HashMap<>();

        for (char x : s.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1); //map.getOrDefault(x, 0) 키값 x를 가져오는데, 맨 처음 키값이 없으면 0을 넣는다.
        }

        //map.containsKey(key);
        //System.out.println(map.containsKey('A')); //키가 있는지 true/false 리턴
        //System.out.println(map.containsKey('F'));

        //map.size();
        //System.out.println(map.size()); //키의 개수(종류)를 리턴

        //map.remove(key);
        //System.out.println(map.remove('A'));  //특정 키 삭제 - 삭제된 갯수 리턴

        int max = Integer.MIN_VALUE;

        //맵 탐색
        for (char key : map.keySet()) {
            //System.out.println(key + " : " + map.get(key));
            if (map.get(key) > max) {
                max = map.get(key);
                answer = key;
            }
        }

        return answer;
    }
/*
15
BACBACCACCBDEDE

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        System.out.println(solution(n, s));
    }
}
