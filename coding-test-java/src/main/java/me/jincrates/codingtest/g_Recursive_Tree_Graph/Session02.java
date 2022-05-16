package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.Scanner;

//2. 이진수 출력
public class Session02 {
    public static void DFS(int n) {
        if (n == 0) {
            return;
        } else {
            //재귀함수 이후 출력라인을 작성해야 스택으로 역순 출력
            DFS(n / 2);
            System.out.print(n % 2 + " ");
        }
    }

/*
3
*/
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DFS(n);
    }
}