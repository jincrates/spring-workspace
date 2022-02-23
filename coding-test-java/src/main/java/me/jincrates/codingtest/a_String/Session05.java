package me.jincrates.codingtest.a_String;

import java.util.ArrayList;
import java.util.Scanner;

//5. 특정문자 뒤집기
//https://cote.inflearn.com/contest/10/problem/01-05
public class Session05 {

    public static String solution(String str) {
        String answer = "";

        char[] s = str.toCharArray();
        int lt = 0, rt = str.length() - 1;
        while (lt < rt) {
            //알파멧이 아닐때
            if (!Character.isAlphabetic(s[lt])) {
                lt++;
            } else if (!Character.isAlphabetic(s[rt])) {
                rt--;
            } else {
                char temp = s[lt];
                s[lt] = s[rt];
                s[rt] = temp;
                lt++;
                rt--;
            }
        }

        answer = String.valueOf(s);;

        return answer;
    }

    //a#b!GE*T@S
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(solution(str));
    }
}
