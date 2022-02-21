package me.jincrates.codingtest.a_String;

import java.util.Scanner;

//2. 대소문자 변환
//https://cote.inflearn.com/contest/10/problem/01-02
public class Session02 {

    public static String solution(String str) {
        String answer = "";

        for (char x : str.toCharArray()) {

            if (Character.isLowerCase(x)) {
                answer += Character.toUpperCase(x);
            } else {
                answer += Character.toLowerCase(x);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.next();

        System.out.println(solution(str));
    }
}
