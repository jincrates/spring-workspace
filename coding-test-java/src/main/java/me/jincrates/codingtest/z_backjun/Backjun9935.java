package me.jincrates.codingtest.z_backjun;

import java.util.Scanner;

//9935. 문자열 폭발
//https://www.acmicpc.net/problem/9935
public class Backjun9935 {

    public static String solution(String str, String bomb) {
        String answer = "FRULA";

        //문자열에 폭탄(bomb)가 포함될 때
        while(str.contains(bomb)) {
            //폭탄 제거
            str = str.replace(bomb, "");
        }

        if(str.length() > 0) {
            answer = str;
        }

        return answer;
    }

    //mirkovC4nizCC44
    //C4
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        String bomb = kb.nextLine();
        System.out.println(solution(str, bomb));
    }

    //메모리 초과 오류 발생
    //CCCCCCCCCCC44444444444  이럴 경우 메모리 누수 발생할 수 있을듯
}
