package me.jincrates.codingtest.z_backjun;

import java.util.Scanner;

//9935. 문자열 폭발
//https://www.acmicpc.net/problem/9935
public class Backjun9935 {

    public static String solution(String str, String bomb) {
        String answer = "FRULA";
        int strLen = str.length();
        int bombLen = bomb.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strLen; i++) {
            char c = str.charAt(i);
            sb.append(c);

            if(sb.length() >= bombLen) {
                //폭발 문자열과 서브 문자열이 같은지 검사
                boolean isBomb = true;

                for (int idx = 0; idx < bombLen; idx++) {
                    char c1 = sb.charAt(sb.length() - bombLen + idx);
                    char c2 = bomb.charAt(idx);
                    System.out.println("c1: " + c1);
                    System.out.println("c2: " + c2);
                    if (c1 != c2) {
                        isBomb = false;
                        break;
                    }
                }
                //폭탄일경우
                if (isBomb) {
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
            if (sb.length() > 0) {
                answer = sb.toString();
            }
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
