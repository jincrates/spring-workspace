package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//5. 소수(에라토스테네스 체)
public class Session05 {
    public static int solution(int n) {
        int answer = 0;
        int[] numArr = new int[n + 1]; //n도 포함해서 처리해야하기 때문

        for (int i = 2; i <= n; i++) {
            System.out.println(i + ": " + numArr[i]);
            if (numArr[i] == 0) {
                answer++;

                for (int j = i; j <= n; j = j + i) {  //i의 배수 검증
                    numArr[j] = 1;
                }
            }
        }

        return answer;
    }

    //10
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int total = kb.nextInt();
        System.out.println(solution(total));
    }
}
