package me.jincrates.codingtest.b_Array;

import java.util.ArrayList;
import java.util.Scanner;

//4. 피보나치 수열
public class Session04 {
    public static int[] solution(int num) {
        int[] answer = new int[num];
        answer[0] = 1;
        answer[1] = 1;
        for (int i = 2; i < num; i++) {
            answer[i] = answer[i-2] + answer[i-1];
        }

        return answer;
    }

    //10
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int total = kb.nextInt();

        for (Integer result : solution(total)) {
            System.out.print(result + " ");
        }
    }
}
