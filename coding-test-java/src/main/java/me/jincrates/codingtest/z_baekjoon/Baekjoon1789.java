package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Scanner;

//1789. 수들의 합
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1789 {
    public static long solution2(long n) {
        long i = 0L;

        //n * (n+1) / 2  는 1부터 n까지 합의 공식
        while (i * (i + 1) / 2 <= n) {
            i++;
        }

        return i - 1;
    }

    public static long solution(long n) {
        long answer = 0L;
        long sum = 0L;

        if (n == 1) {
            return 1;
        }

        for (int i = 1; i < n; i++) {
            sum += i;

            if(sum <= n) {
                //System.out.println(i);
                answer++;
            }
            else {
                break;
            }
        }

        return answer;

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        System.out.println(solution2(n));
    }
}

/*
200
*/