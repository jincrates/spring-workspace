package me.jincrates.codingtest.a_String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//11. 문자열 압축
public class Session11 {

    public static String solution(String str) {
        StringBuilder answer = new StringBuilder();
        str += " ";  //빈 문자 추가
        int cnt = 1;

        for(int i = 0, max = str.length() - 1; i < max; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                cnt++;
            } else {
                answer.append(str.charAt(i));
                if(cnt > 1) {
                    answer.append(String.valueOf(cnt));
                    cnt = 1;
                }
            }
        }

        return answer.toString();
    }

    //KKHSSSSSSSE
    //KSTTTSEEKFKKKDJJGG
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.print(solution(str));
    }
}
