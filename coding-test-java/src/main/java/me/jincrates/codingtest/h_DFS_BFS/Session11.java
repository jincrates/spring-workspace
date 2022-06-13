package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//11. 미로의 최단거리 통로(BFS)
public class Session11 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board, dis;

    public void BFS(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        //출발점 체크
        board[x][y] = 1;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            //방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                //유효한 방향이면서
                if (nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7) {
                    //아직 방문하지 않은 좌표일 때
                    if (board[nx][ny] == 0) {
                        //방문 표시
                        board[nx][ny] = 1;
                        queue.offer(new Point(nx, ny));
                        //현재 좌표에 +1하여 다음 좌표에 할당
                        dis[nx][ny] = dis[current.x][current.y] + 1;
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Session11 T = new Session11();
        Scanner sc = new Scanner(System.in);

        board = new int[8][8];  //1번 인덱스부터 사용하기 위해 size 8로 잡음
        dis = new int[8][8];

        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        T.BFS(1, 1);

        if (dis[7][7] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(dis[7][7]);
        }
    }
}

/*
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 1 0 0 0
1 0 0 0 1 0 0
1 0 1 0 0 0 0

*/