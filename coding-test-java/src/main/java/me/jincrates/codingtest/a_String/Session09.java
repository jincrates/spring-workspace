package me.jincrates.codingtest.a_String;

import java.util.Scanner;

//9. 숫자만 추출
public class Session09 {

    public static int solution(String str) {
        int answer = 0;
        String onlyNum = str.replaceAll("[^0-9]", "");
        answer = Integer.parseInt(onlyNum);
        
        return answer;
    }

    //g0en2T0s8eSoft
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(solution(str));
    }
}
