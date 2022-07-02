package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

// 8. 원더랜드(프림: PriorityQueue)
// 프림 알고리즘
class Edge08 implements Comparable<Edge08> {
    public int vex;
    public int cost;
    Edge08(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge08 ob) {
        return this.cost - ob.cost;
    }
}

public class Session08 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 인접리스트 만들기
        ArrayList<ArrayList<Edge08>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge08>());
        }

        int[] ch = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            // 무방향이기 떄문에 양쪽을 넣어준다.
            graph.get(a).add(new Edge08(b, c));
            graph.get(b).add(new Edge08(a, c));
        }

        int answer = 0;
        PriorityQueue<Edge08> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Edge08(1, 0));

        while (!priorityQueue.isEmpty()) {
            Edge08 temp = priorityQueue.poll();
            int ev = temp.vex;  //도착 정점

            if (ch[ev] == 0) {
                ch[ev] = 1;
                answer += temp.cost;

                // 도착 정점에 연결된 간선 정보를 pQ에 넣어주기.
                // 단, 이미 체크된 것은 제외
                for (Edge08 ob : graph.get(ev)) {
                    if (ch[ob.vex] == 0) {
                        priorityQueue.offer(new Edge08(ob.vex, ob.cost));
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

/*
9 12
1 2 12
1 9 25
2 3 10
2 8 17
2 9 8
3 4 18
3 7 55
4 5 44
5 6 60
5 7 38
7 8 35
8 9 15


*/