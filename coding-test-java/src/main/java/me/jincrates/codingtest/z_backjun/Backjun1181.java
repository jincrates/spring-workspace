package me.jincrates.codingtest.z_backjun;

import java.io.*;
import java.util.*;

//1181. 단어 정렬
//https://www.acmicpc.net/problem/@@@@
public class Backjun1181 {
    int [] arr;
    public static ArrayList<String> solution(int n, String[] arr) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                //길이가 짧은 것부터
                if (arr[j].length() > arr[j + 1].length()) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //길이가 같으면 사전순
                } else if (arr[j].length() == arr[j + 1].length()) {

                    //첫글자 비교
                    if (arr[j].charAt(0) > arr[j + 1].charAt(0)) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    } else if (arr[j].charAt(0) == arr[j + 1].charAt(0)) {
                        if (arr[j].charAt(1) > arr[j + 1].charAt(1)) {
                            String temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        } else if (arr[j].charAt(1) == arr[j + 1].charAt(1)) {
                            String temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }

                }
            }
        }

//        answer = arr;
//
//        return arr;
        return null;
    }




/*
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours
 */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = st.nextToken();
        }
        
        for (String x: solution(n, arr)) {
            System.out.println(x);
        }
        
/*
        String[] result = solution(n, arr);
        System.out.println(result[0]);
        for(int i = 1; i < n; i++) {
            if(arr[i-1].equals(arr[i])) {
                continue;
            }
            System.out.println(arr[i]);
        }
 */
    }
}