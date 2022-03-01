package me.jincrates.codingtest.b_Array;

import java.util.ArrayList;
import java.util.Scanner;

//3. 가위바위보
public class Session03 {
    public static ArrayList<String> solution(int[] num1, int[] num2) {
        ArrayList<String> answer = new ArrayList<>();
        
        for (int i = 0; i < num1.length; i++) {
            if (num1[i] == num2[i]) {
                answer.add("D");
            } else if (num1[i] == 1 && num2[i] == 3) {
                answer.add("A");
            } else if (num1[i] == 2 && num2[i] == 1) {
                answer.add("A");
            } else if (num1[i] == 3 && num2[i] == 2) {
                answer.add("A");
            } else {
                answer.add("B");
            }
        }

        return answer;
    }

    // 5
    // 2 3 3 1 3
    // 1 1 2 2 3
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int total = kb.nextInt();
        int[] num1 = new int[total];
        int[] num2 = new int[total];

        for (int i = 0; i < total; i++) {
            num1[i] = kb.nextInt();
        }
        for (int i = 0; i < total; i++) {
            num2[i] = kb.nextInt();
        }

        for (String result : solution(num1, num2)) {
            System.out.println(result);
        }
    }
}
