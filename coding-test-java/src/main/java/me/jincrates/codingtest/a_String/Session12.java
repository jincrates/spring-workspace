package me.jincrates.codingtest.a_String;

import java.util.Scanner;

//12. 암호
public class Session12 {

    public static String solution(int num, String str) {
        String answer = "";

        for (int i = 0; i < num; i++) {
            String temp = str.substring(0, 7).replace("#", "1").replace("*", "0");
            int n = Integer.parseInt(temp, 2); // 2진수를 10진수로 
            answer += (char) n;

            str = str.substring(7);
        }

        return answer;
    }

    // 4
    // #****###**#####**#####**##**
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        String str = kb.next();
        System.out.print(solution(num, str));
    }
}
