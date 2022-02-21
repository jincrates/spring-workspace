package me.jincrates.codingtest.a_String;


import java.util.Scanner;

//2. 대소문자 변환
//https://cote.inflearn.com/contest/10/problem/01-02
public class Session02 {

    public static String solution(String str) {
        int ascii = 0;
        String result = "";

        for (char x : str.toCharArray()) {
            ascii = (int) x;

            //97보다 작으면 대문자
            if(ascii < 97) {
                result += Character.toLowerCase(x);
            } else {
                result += Character.toUpperCase(x);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.next();

        System.out.println(solution(str));
    }
}
