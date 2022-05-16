package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.Scanner;

//3. 팩토리얼
public class Session03 {
    public static int DFS(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * DFS(n - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(DFS(n));
    }
}