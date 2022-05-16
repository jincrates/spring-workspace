package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.Scanner;

//4. 피보나치 수열
//for, array가 재귀함수보다 성능이 더 좋다. 재귀함수는 스택 쓰고하느라 성능이 떨어짐
public class Session04 {
    //중간 결과값들을 저장하기 위해 변수 선언
    static int[] fibo;

    public static int DFS(int n) {
        //3. 메모이제이션: 이미 구한 값을 다시 구하지 말고 그대로 사용
        //이미 가지가 뻣어나가서 값이 구해진 상황
        if (fibo[n] > 0) {
            return fibo[n];
        }

        //2. 전역변수를 선언하여 해당 변수를 이용(매번 함수 호출해서 구하지말고)
        if (n == 1) {
            return fibo[n] = 1;
        } else if (n == 2) {
            return fibo[n] = 1;
        } else {
            return fibo[n] = DFS(n - 2) + DFS(n - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        fibo = new int[n + 1];
        DFS(n);

        for (int i = 1; i <= n; i++) {
            //System.out.print(DFS(i) + " ");  // 1. 숫자 늘어나면 성능최악
            System.out.print(fibo[i] + " ");
        }
    }
}