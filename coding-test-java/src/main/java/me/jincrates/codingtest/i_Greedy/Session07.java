package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 7. 원더랜드(크루스칼: Union & Find)
// 최소스패닝 트리: 가중치 값이 최소찾기
// 그래프: 회로가 존재, 트리: 회로가 존재하지 않음
// 크루스칼 알고리즘 사용
class Edge07 implements Comparable<Edge07> {
    public int v1;   // 정점1
    public int v2;   // 정점2
    public int cost; // 가중치
    Edge07(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge07 ob) {
        // 가중치 오름차순
        return this.cost - ob.cost;
    }
}

public class Session07 {
    static int[] unf;

    public static int find(int v) {
        if (v == unf[v]) {
            return v;
        } else {
            return unf[v] = find(unf[v]);
        }
    }

    // a와 b를 하나의 집합으로 union
    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 도시의 개수
        int m = sc.nextInt();

        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }

        // 간선 객체를 담은 ArrayList 생성
        ArrayList<Edge07> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new Edge07(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        int answer = 0;
        int cnt = 0; // 간선의 개수
        Collections.sort(list); // cost에 의해 오름차순

        for (Edge07 ob : list) {
            // 트리의 간선 개수는 n-1개 : 반복문을 줄이기 위해 작성
            if (cnt == n-1) {
                break;
            }

            int fv1 = find(ob.v1);
            int fv2 = find(ob.v2);

            if (fv1 != fv2) {
                answer += ob.cost;
                // 가중치를 더하고나서 하나의 집합으로 묶어줘야한다.
                union(ob.v1, ob.v2);
                cnt++;
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