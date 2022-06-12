package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Scanner;

//10. 미로탐색
public class Session10 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int answer = 0;

    public void DFS(int x, int y) {
        //도착시 answer 증가
        if (x == 7 && y == 7) {
            answer++;
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                //경계선 안에 있는지 확인 && 갈 수 있는 지점인지 확인
                if (nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7
                        && board[nx][ny] == 0) {
                    //지나간 자리 체크
                    board[nx][ny] = 1;
                    DFS(nx, ny);
                    //DFS() 후 체크 풀어주기
                    board[nx][ny] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session10 main = new Session10();
        Scanner sc = new Scanner(System.in);

        board = new int[8][8];  //1번 인덱스부터 사용하기 위해 size 8로 잡음
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        board[1][1] = 1;
        main.DFS(1, 1);

        System.out.println(answer);
    }
}

/*
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 0 0 0 1
1 1 0 1 1 0 0
1 0 0 0 0 0 0

*/
