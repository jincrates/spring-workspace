package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//13. 섬나라 아일랜드(DFS, BFS)
public class Session13 {
    static int n, answer = 0;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    //BFS
    Queue<Point> queue = new LinkedList<>();

    public void DFS(int x, int y, int[][] board) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    DFS(nx, ny, board);
                }
            }
        }
    }

    public void BFS(int x, int y, int[][] board) {
        //첫번째 위치를 넣는다.
        queue.add(new Point(x, y));

        while(!queue.isEmpty()) {
            Point pos = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (board[nx][ny] == 1) {
                        board[nx][ny] = 0;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public void countIsland(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    answer++;
                    board[i][j] = 0;
                    //DFS(i, j, board);
                    BFS(i, j, board);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session13 T = new Session13();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        T.countIsland(arr);
        System.out.println(answer);
    }
}

/*
7
1 1 0 0 0 1 0
0 1 1 0 1 1 0
0 1 0 0 0 0 0
0 0 0 1 0 1 1
1 1 0 1 1 0 0
1 0 0 0 1 0 0
1 0 1 0 1 0 0

*/
