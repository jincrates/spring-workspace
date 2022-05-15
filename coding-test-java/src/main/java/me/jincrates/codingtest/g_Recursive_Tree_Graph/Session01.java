package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.Scanner;

//1. 재귀함수
public class Session01 {
    public static void DFS(int n) {
        if (n == 0) {
            return;
        } else {
            System.out.print(n + " ");
            DFS(n - 1);
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