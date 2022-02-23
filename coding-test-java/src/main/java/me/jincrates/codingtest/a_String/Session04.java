package me.jincrates.codingtest.a_String;

import java.util.ArrayList;
import java.util.Scanner;

//4. 단어 뒤집기(StringBuilder 이용법 또는 직접 뒤집기)
//https://cote.inflearn.com/contest/10/problem/01-04
public class Session04 {

    public static ArrayList<String> solution(int n, String[] strArray) {
        ArrayList<String> answer = new ArrayList<>();
        for (String x : strArray) {
            //1. StringBuilder 사용
            //String temp = new StringBuilder(x).reverse().toString();
            //answer.add(temp);

            //2. 직접 뒤집기
            char[] s = x.toCharArray();
            int lt = 0, rt = x.length() - 1;
            while (lt < rt) {
                char temp = s[lt];
                s[lt] = s[rt];
                s[rt] = temp;
                lt++;
                rt--;
            }
            String str = String.valueOf(s);
            answer.add(str);
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String[] array = new String[n];

        for (int i = 0; i < n; i++){
            array[i] = kb.next();
        }

        for (String x : solution(n, array)) {
            System.out.println(x);
        }
    }
}
