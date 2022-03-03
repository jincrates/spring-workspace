package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//5. 소수(에라토스테네스 체)
public class Session05 {
    public static int solution(int n) {
        int answer = 0;
        int[] ch = new int[n+1];

        for (int i = 2; i <= n; i++) {
            if (ch[i] == 0) {  //소수
                answer++;

                for (int j = i; j <= n; j=j+i) {  //i의 배수 제거
                    ch[j] = 1;
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
