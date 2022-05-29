package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//12. 경로탐색(인접리스트, ArrayList)
//정점이 천개 만개 일때 인접행렬로는 너무 비효율적이다.(시간복잡도, 메모리)
public class Session12 {
    static int n, m, answer = 0;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] check;

    public void DFS(int v) {
        if (v == n) {
            answer++;
        } else {
            for (int nv : graph.get(v)) {
                if (check[nv] == 0) {
                    check[nv] = 1;
                    DFS(nv);
                    check[nv] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session12 main = new Session12();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        check = new int[n + 1];

        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
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