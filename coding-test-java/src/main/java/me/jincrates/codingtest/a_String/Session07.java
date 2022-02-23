package me.jincrates.codingtest.a_String;

import java.util.Scanner;

//7. 회문문자열
public class Session07 {

    public static String solution(String str) {
        String answer = "YES";
        /*
        //배열을 만든다.
        char[] c = str.toCharArray();
        //좌우 인덱스
        int lt = 0, rt = str.length() - 1;

        while (lt < rt) {
            //대소문자 구분
            //if (c[lt] != c[rt]) {
            //대소문자 구분안함
            if (Character.toUpperCase(c[lt]) != Character.toUpperCase(c[rt])) {
                return "NO";
            }

            lt++;
            rt--;
        }
        */
        String temp = new StringBuilder(str).reverse().toString();
        if (!str.equalsIgnoreCase(temp)) {
            return "NO";
        }

        return answer;
    }

    //teNeT
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(solution(str));
    }
}
