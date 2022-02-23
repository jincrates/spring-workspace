package me.jincrates.codingtest.a_String;

import java.util.*;

//6. 중복문자 제거
public class Session06 {

    public static String solution(String str) {
        String answer = "";

        for (char c : str.toCharArray()) {

            //해당 문자가 포함되어 있는지 검증
            if (!answer.contains(String.valueOf(c))) {
                //포함되지 않았을 때만 넣어준다.
                answer += c;
            }
        }

        return answer;
    }

    //ksekkset
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(solution(str));
    }
}
