package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//15. 피자배달거리(DFS)
public class Session14 {
    static int n, m, len, answer = Integer.MAX_VALUE;
    static int[] combi;
    static ArrayList<Point> hs, pz;
    public void DFS(int L, int s) {
        if (L == m) {
            int sum = 0;

            //집과 살아남은 피자집들과의 거리차이 구하기
            for (Point h : hs) {
                int dis = Integer.MAX_VALUE;
                for (int i : combi) {
                    int diff = Math.abs(h.x - pz.get(i).x) + Math.abs(h.y - pz.get(i).y);
                    dis = Math.min(dis, diff);
                }
                sum += dis; //도시의 피자배달거리
            }

            answer = Math.min(answer, sum);
        } else {
            //조합 만들기
            for (int i = s; i < len; i++) {
                combi[L] = i;
                DFS(L + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session14 T = new Session14();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pz = new ArrayList<>();
        hs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = sc.nextInt();

                if (temp == 1) hs.add(new Point(i, j));
                else if (temp == 2) pz.add(new Point(i, j));
            }
        }

        len = pz.size(); //피자집의 개수  len_C_m
        combi = new int[m];
        T.DFS(0, 0);
        System.out.println(answer);
    }
}

/*
4 4
0 1 2 0
1 0 2 1
0 2 1 2
2 0 1 2


*/
