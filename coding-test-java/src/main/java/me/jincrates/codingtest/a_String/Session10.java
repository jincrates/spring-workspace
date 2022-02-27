package me.jincrates.codingtest.a_String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//10. 가장 짧은 문자거리
public class Session10 {

    public static int[] solution(String str, char c) {
        int[] answer = new int[str.length()];
        int p = 1000;
        for (int i = 0, max = str.length(); i < max; i++) {
            if (str.charAt(i) == c) {
                p = 0;
                answer[i] = p;
            } else {
                p++;
                answer[i] = p;
            }
        }

        //다시 오른쪽부터
        p = 1000;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == c) {
                p = 0;
            } else {
                p++;
                answer[i] = Math.min(answer[i], p);
            }
        }

        return answer;
    }

    //teachermode e
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        char c = kb.next().charAt(0);
        for (int x : solution(str, c)) {
            System.out.print(x + " ");
        }
    }
}
