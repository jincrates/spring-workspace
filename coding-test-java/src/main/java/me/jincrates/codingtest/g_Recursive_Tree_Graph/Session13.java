package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//13. 그래프 최단거리(BFS)
public class Session13 {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] check, distance;

    public void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        check[v] = 1;
        distance[v] = 0;
        queue.offer(v);

        while (!queue.isEmpty()) {
            int cv = queue.poll();
            for (int nv : graph.get(cv)) {
                if (check[nv] == 0) {
                    check[nv] = 1;
                    queue.offer(nv);
                    distance[nv] = distance[cv] + 1;  //이 원리를 알아야함
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session13 main = new Session13();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        check = new int[n + 1];
        distance = new int[n + 1];
        graph = new ArrayList<ArrayList<Integer>>();
        
        for (int  i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }
        main.BFS(1);
        
        for (int i = 2; i <= n; i++) {
            System.out.println(i + " : " + distance[i]);
        }
    }
}

/*
6 9
1 3
1 4
2 1
2 5
3 4
4 5
4 6
6 2
6 5
*/