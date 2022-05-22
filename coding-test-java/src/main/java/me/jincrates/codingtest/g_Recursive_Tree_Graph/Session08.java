package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//8. 송아지 찾기1(BFS)
public class Session08 {
    int answer = 0;
    int[] distance = {1, -1, 5};
    int[] check;
    Queue<Integer> queue = new LinkedList<>();

    public int BFS(int start, int end) {
        check = new int[10001];
        check[start] = 1;
        queue.offer(start);
        int level = 0;
        while (!queue.isEmpty()) {
            //해당 level에 있는 원소의 개수
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int x = queue.poll();

                //갈 수 있는 경우의 수
                for (int d : distance) {
                    int nx = x + d;

                    if (nx == end) {
                        return level + 1;  //x까지 갈 수 있는 거리
                    }

                    //문제에서 주어진 좌표의 범위
                    if (nx >= 1 && nx <= 10000 && check[nx] == 0) {
                        check[nx] = 1;
                        queue.offer(nx);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    // 5 14
    public static void main(String[] args) throws IOException {
        Session08 main = new Session08();
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        System.out.println(main.BFS(start, end));
    }
}