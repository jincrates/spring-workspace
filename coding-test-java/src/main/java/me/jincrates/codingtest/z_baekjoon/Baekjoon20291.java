package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//20291. 파일정리
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon20291 {

/*
8
sbrus.txt
spc.spc
acm.icpc
korea.icpc
sample.txt
hello.world
sogang.spc
example.txt
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        //map 생성
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            //split을 사용하여 확장자를 map에 담기
            String ext = new StringTokenizer(br.readLine()).nextToken().split("\\.")[1];
            map.put(ext, map.getOrDefault(ext, 0) + 1);
        }

        //키를 담을 리스트를 만들어서 오름차순
        List<String> listKey = new ArrayList<>(map.keySet());
        Collections.sort(listKey);

        //결과 출력
        for(String key : listKey) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
