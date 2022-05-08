package me.jincrates.codingtest.z_baekjoon;

import java.io.*;
import java.util.*;

//11728. 배열합치기
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon11728 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String [] line1 = br.readLine().split(" ");
        int size1 = Integer.parseInt(line1[0]);
        int size2 = Integer.parseInt(line1[1]);
        int[] result = new int[size1 + size2];

        //다음 줄
        String[] line2 = br.readLine().split(" ");
        String[] line3 = br.readLine().split(" ");

        int idx = 0;
        for (int i = 0; i < size1; i++) {
            result[idx++] = Integer.parseInt(line2[i]);
        }
        for (int i = 0; i < size2; i++) {
            result[idx++] = Integer.parseInt(line3[i]);
        }

        Arrays.sort(result); // 오름차순 정렬
        for(int r : result) {
            bw.write(String.valueOf(r) + " ");
        }

        bw.flush();
        bw.close();
    }
}