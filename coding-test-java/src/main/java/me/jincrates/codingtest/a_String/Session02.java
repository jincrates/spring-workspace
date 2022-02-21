package me.jincrates.codingtest.a_String;


import java.util.Scanner;

//2. 대소문자 변환
//https://cote.inflearn.com/contest/10/problem/01-02
public class Session02 {

    public static String solution(String str) {
        int ascii = 0;

        for (int i = 0, max = str.length(); i < max; i++) {
            ascii = (int) str.charAt(i);
            System.out.println(str.charAt(i) + " : " +  ascii);

            //97보다 작으면 대문자
            if(ascii < 97) {
                str = str.replace(str.charAt(i), Character.toLowerCase(str.charAt(i)));
            } else {
                str = str.replace(str.charAt(i), Character.toUpperCase(str.charAt(i)));
            }
        }

        return str;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.next();

        System.out.println(solution(str));
    }
}
