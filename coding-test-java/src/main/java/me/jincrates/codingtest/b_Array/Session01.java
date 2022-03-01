package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//1. 큰 수 출력하기
public class Session01 {
    public static String solution(int[] num) {
        String answer = "" + num[0];

        int temp = num[0];

        for (int i = 0; i < num.length; i++) {
            if (temp < num[i]) {
                answer += " " + num[i];
            }
            temp = num[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int total = kb.nextInt();
        int[] num = new int[total];

        for (int i = 0; i < total; i++) {
            num[i] = kb.nextInt();
        }

        System.out.print(solution(num));
    }
}
