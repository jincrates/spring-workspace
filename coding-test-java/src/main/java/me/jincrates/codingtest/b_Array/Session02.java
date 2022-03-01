package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//2. 보이는 학생
public class Session02 {
    public static int solution(int[] num) {
        int answer = 0;
        int temp = num[0];

        for (int i = 0; i < num.length; i++) {
            if (temp < num[i]) {
                answer++;
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
