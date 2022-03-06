package me.jincrates.codingtest.b_Array;

import java.util.Scanner;

//6. 뒤집은 소스
public class Session06 {
    public static void solution(int n, String[] arr) {
        String answer = "";
        int reverse = Integer.MIN_VALUE;
        for(String num : arr) {
            reverse = Integer.parseInt(new StringBuilder(num).reverse().toString());

            if (isPrime(reverse)) {
               answer += String.valueOf(reverse) + ' ';
            }
        }

        System.out.println(answer);
    }

    public static boolean isPrime(int num) {
        if(num == 1) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
/*
9
32 55 62 20 250 370 200 30 100
 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.next();
        }
        solution(n, arr);
    }
}
