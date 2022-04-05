package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//3. 크레인 인형뽑기(카카오)
public class Session03 {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> score = new Stack<>();
        Stack<Integer> line1 = new Stack<>();
        Stack<Integer> line2 = new Stack<>();
        Stack<Integer> line3 = new Stack<>();
        Stack<Integer> line4 = new Stack<>();
        Stack<Integer> line5 = new Stack<>();

        for (int i = board.length - 1, min = 0; i >= min; i--) {
            if (board[i][0] != 0) {
                line1.push(board[i][0]);
            }
            if (board[i][1] != 0) {
                line2.push(board[i][1]);
            }
            if (board[i][2] != 0) {
                line3.push(board[i][2]);
            }
            if (board[i][3] != 0) {
                line4.push(board[i][3]);
            }
            if (board[i][4] != 0) {
                line5.push(board[i][4]);
            }
        }
        /*
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        */
        for (int i = 0, max = moves.length; i < max; i++) {
            int popElement = 0;

            switch (moves[i]) {
                case 1:
                    if (!line1.isEmpty()) {
                        popElement = line1.pop();
                        if (((score.size() == 0) ? 0 : score.lastElement()) == popElement) {
                            answer += 2;
                            score.pop();
                        } else {
                            score.push(popElement);
                        }
                    }
                    break;
                case 2:
                    if (!line2.isEmpty()) {
                        popElement = line2.pop();
                        if (((score.size() == 0) ? 0 : score.lastElement()) == popElement) {
                            answer += 2;
                            score.pop();
                        } else {
                            score.push(popElement);
                        }
                    }
                    break;
                case 3:
                    if (!line3.isEmpty()) {
                        popElement = line3.pop();
                        if (((score.size() == 0) ? 0 : score.lastElement()) == popElement) {
                            answer += 2;
                            score.pop();
                        } else {
                            score.push(popElement);
                        }
                    }
                    break;
                case 4:
                    if (!line4.isEmpty()) {
                        popElement = line4.pop();
                        if (((score.size() == 0) ? 0 : score.lastElement()) == popElement) {
                            answer += 2;
                            score.pop();
                        } else {
                            score.push(popElement);
                        }
                    }
                    break;
                case 5:
                    if (!line5.isEmpty()) {
                        popElement = line5.pop();
                        if (((score.size() == 0) ? 0 : score.lastElement()) == popElement) {
                            answer += 2;
                            score.pop();
                        } else {
                            score.push(popElement);
                        }
                    }
                    break;
            }
        }

        return answer;
    }

/*
5
0 0 0 0 0
0 0 1 0 3
0 2 5 0 1
4 2 4 4 2
3 5 1 3 1
8
1 5 3 5 1 2 1 4

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line1 = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(line1[j]);
            }
        }

        int m = Integer.parseInt(br.readLine());
        int[] moves = new int[m];

        String[] line2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            moves[i] = Integer.parseInt(line2[i]);
        }

        System.out.println(solution(board, moves));
    }
}