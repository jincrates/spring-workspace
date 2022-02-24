package me.jincrates.codingtest.a_String;

import java.util.Locale;
import java.util.Scanner;

//8. 유효한 팰린드롬
public class Session08 {

    public static String solution(String str) {
        String answer = "YES";
        String onlyAlphabet = str.toUpperCase().replaceAll("[^A-Z]", "");
        String reverseStr = new StringBuilder(onlyAlphabet).reverse().toString();

        if (!onlyAlphabet.equalsIgnoreCase(reverseStr)) {
            answer = "NO";
        }

        return answer;
    }

    //found7, time: study; Yduts; emit, 7Dnuof
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(solution(str));
    }
}
