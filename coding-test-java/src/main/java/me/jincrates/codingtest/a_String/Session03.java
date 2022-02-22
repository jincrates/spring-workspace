package me.jincrates.codingtest.a_String;

import java.util.Scanner;

//3. 문장 속의 단어(indexOf(), substring())
//https://cote.inflearn.com/contest/10/problem/01-03
public class Session03 {

    public static String solution(String str) {
        String answer = "";
        int min = Integer.MIN_VALUE;
        String[] strArray = str.split(" ");

        for (String x : strArray) {
            int len = x.length();
            if (len > min) {
                min = len;
                answer = x;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();  // 입력한 한줄을 받음
        System.out.println(solution(str));
    }
}
