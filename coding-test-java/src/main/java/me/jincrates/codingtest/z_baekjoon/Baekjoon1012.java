package me.jincrates.codingtest.z_baekjoon;

import java.util.Scanner;

//1012. 유기농 배추
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1012 {
    static int M, N, K;
    static int[][] cabbage, check;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int answer = 0;

    public void DFS(int x, int y) {
        check[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (cabbage[nx][ny] == 1 && check[nx][ny] == 0) {
                    DFS(nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon1012 T = new Baekjoon1012();
        Scanner sc = new Scanner(System.in);
        int testCaseCnt = sc.nextInt(); //테스트 케이스 개수

        for (int i = 0; i < testCaseCnt; i++) {
            M = sc.nextInt();  //배추같 가로 길이
            N = sc.nextInt();  //배추밭 세로 길이
            K = sc.nextInt();  //배추가 심어져 있는 위치 개수

            //배추 밭
            cabbage = new int[M][N];
            check = new int[M][N];
            for (int j = 0; j < K; j++) {
                cabbage[sc.nextInt()][sc.nextInt()] = 1;
            }

            for (int x = 0; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    if (cabbage[x][y] == 1 && check[x][y] == 0) {
                        answer++;
                        T.DFS(x, y);
                    }
                }
            }

            System.out.println(answer);
            answer = 0;
        }
    }
}


/*
1
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6

2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5

1
5 3 6
0 2
1 2
2 2
3 2
4 2
4 0


 */