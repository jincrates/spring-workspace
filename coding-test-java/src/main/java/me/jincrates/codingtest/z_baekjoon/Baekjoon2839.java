package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Scanner;

//2839. 설탕배달
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon2839 {

    public static int solution(int sugar) {
        int answer = 0;

        while (sugar >= 0) {

            //5로 딱 나누어 떨어진 경우
            if (sugar % 5 == 0) {
                answer += sugar / 5;
                sugar = 0;  //여기서 안빼주면 밑에서 -1 나옴...
                break;
            } else {
                sugar -= 3;
                answer += 1;
            }
        }

        //딱 나누어 떨어지지 않은 경우
        if (sugar != 0) {
            answer = -1;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }
}

/*
18
*/