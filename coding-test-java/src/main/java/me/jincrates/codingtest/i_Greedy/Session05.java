package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    public int vex;  // 정점
    public int cost; // 비용
    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge ob) {
        //오름차순: 가장 작은 값은 우선
        return this.cost - ob.cost;
    }
}

// 5. 다익스트라 알고리즘
// 간선에 가중치 값이 음수가 있으면 안 된다.
public class Session05 {
    static int n, m;
    static int[] dis;
    static ArrayList<ArrayList<Edge>> graph;

    public static void solution(int v) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(v, 0));
        dis[v] = 0;
        while (!pQ.isEmpty()) {
            Edge temp = pQ.poll();
            int now = temp.vex;
            int nowCost = temp.cost;

            if (nowCost > dis[now]) {
                continue;
            };

            //해당 정점의 비용을 계산
            for (Edge ob : graph.get(now)) {
                if (dis[ob.vex] > nowCost + ob.cost) {
                    dis[ob.vex] = nowCost + ob.cost;
                    pQ.offer(new Edge(ob.vex, nowCost + ob.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  // 정점의 개수
        m = sc.nextInt();  // 간선의 개수

        graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }

        dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);  //최대값을 채워넣는다.
        for (int i = 0; i <= n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
        }

        solution(1);
        for (int i = 2; i <= n; i++) {
            if (dis[i] != Integer.MAX_VALUE) {
                System.out.println(i + " : " + dis[i]);
            } else {
                System.out.println(i + " : " + "impossible");
            }
        }

    }
}

/*
6 9
1 2 12
1 3 4
2 1 2
2 3 5
2 5 5
3 4 5
4 2 2
4 5 5
6 4 5


*/