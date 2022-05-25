package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//11. 경로탐색(DFS)
//경로상 한번 방문한 노드는 방문하지 않는다.(무한으로 빠지기 때문)
public class Session11 {
    static int n, m, answer = 0;
    static int[][] graph;
    static int[] check;
    
    public void DFS(int v) {
        if (v == n) {
            answer++;
        } else {
            for (int i = 1; i <=n; i++) {
                //현재지점 v에서 i로 탐색
                if (graph[v][i] == 1 && check[i] ==0 ) {
                    check[i] = 1;
                    DFS(i);
                    check[i] = 0; //DFS 탐색 종료후 check를 풀어줘야함
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session11 main = new Session11();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n + 1][n + 1];  //index가 n까지 있어야하기 때문
        check = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
        }
        check[1] = 1;
        main.DFS(1);
        System.out.println(answer);
    }
}

/*
5 9
1 2
1 3
1 4
2 1
2 3
2 5
3 4
4 2
4 5
*/