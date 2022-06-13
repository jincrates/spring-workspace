package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//12. 토마토(BFS 활용)
public class Session11 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board, tomato;  //토마토가 익는 날 수
    static int n, m;
    static Queue<Point> queue = new LinkedList<>();

    public void BFS() {
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            //방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                //유효한 방향이면서
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    //아직 방문하지 않은 좌표일 때
                    if (board[nx][ny] == 0) {
                        //방문 표시
                        board[nx][ny] = 1;
                        queue.offer(new Point(nx, ny));
                        //현재 좌표에 +1하여 다음 좌표에 할당
                        tomato[nx][ny] = tomato[current.x][current.y] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session11 T = new Session11();
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        board = new int[n][m];
        tomato = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();

                //BFS가 돌기 전에 시작 지점을 미리 넣어두는 것이 포인트
                if (board[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        T.BFS();
        boolean flag = true;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    flag = false;
                }
            }
        }

        if (flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    answer = Math.max(answer, tomato[i][j]);
                }
            }
        } else {
            answer = -1;
        }
        System.out.println(answer);
    }
}

/*
6 4
0 0 -1 0 0 0
0 0 1 0 -1 0
0 0 -1 0 0 0
0 0 0 0 -1 1


*/
