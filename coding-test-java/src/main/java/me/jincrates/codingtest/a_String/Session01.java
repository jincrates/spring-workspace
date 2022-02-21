package me.jincrates.codingtest.a_String;

import java.util.Locale;
import java.util.Scanner;

public class Session01 {

    public static int solution(String str, char t) {
        int answer = 0;

        //대문자로 변환
        str = str.toUpperCase();
        t = Character.toUpperCase(t);

        for (int i = 0, max = str.length(); i < max; i++) {
            if (str.charAt(i) == t) {
               answer += 1;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        char c = kb.next().charAt(0);

        System.out.println(solution(str, c));
    }
}
