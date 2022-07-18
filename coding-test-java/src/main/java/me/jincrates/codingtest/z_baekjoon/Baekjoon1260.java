package me.jincrates.codingtest.z_baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//1260. DFS와 BFS
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1260 {
    static int n, m, v, answer = 0;
    static int[][] graph;
    static int[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  //정점 수
        m = sc.nextInt();  //간선 수
        v = sc.nextInt();  //시작정점

        graph = new int[n + 1][n + 1];
        check = new int[n + 1];

        for (int i = 0; i < m; i ++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            //양방향
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        check[v] = 1;
        DFS(v);

        System.out.println();

        BFS(v);
    }

    public static void DFS(int v) {
        if (v == m) {
            return;
        } else {
            System.out.print(v + " ");

            for (int i = 1; i <= n; i++) {
                if (graph[v][i] == 1 && check[i] == 0) {
                    check[i] = 1;
                    DFS(i);
                    check[i] = 0;
                }
            }
        }
    }

    public static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        check[v] = 1;

        while(!queue.isEmpty()) {
            int cv = queue.poll();
            System.out.print(cv + " ");

            for (int i = 1; i <= n; i++) {
                if (graph[cv][i] == 1 && check[i] == 0) {
                    queue.offer(i);
                    check[i] = 1;
                }
            }
        }
    }
}


/*
4 5 1
1 2
1 3
1 4
2 4
3 4

5 5 3
5 4
5 2
1 2
3 4
3 1

 */