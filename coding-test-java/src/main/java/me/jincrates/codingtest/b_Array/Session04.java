package me.jincrates.codingtest.b_Array;

import java.util.ArrayList;
import java.util.Scanner;

//4. 피보나치 수열(배열 안쓰고)
public class Session04 {
    public static void solution(int num) {
        int a = 1, b = 1, c;
        System.out.print(a + " " + b);

        for (int i = 2; i < num; i++) {
            c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }
    }

    //10
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int total = kb.nextInt();
        solution(total);
    }
}
