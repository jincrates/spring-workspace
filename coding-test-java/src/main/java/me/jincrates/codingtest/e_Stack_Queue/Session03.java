package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//3. 크레인 인형뽑기(카카오)
public class Session03 {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        //moves 탐색 - pos: 크레인 위치
        for (int pos : moves) {
            for (int i = 0, max = board.length; i < max; i++) {
                if (board[i][pos - 1] != 0) {
                    //선택된 인형 번호
                    int temp = board[i][pos - 1];
                    //선택하고 난 뒤에는 그 자리에 0을 채워넣음
                    board[i][pos - 1] = 0;

                    //선택된 인형 번호와 스택 제일 상단에 있는 인형 수가 같은지 비교
                    //stack.peek() : 꺼내지 않고 값만 확인
                    if (!stack.isEmpty() && temp == stack.peek()) {
                        answer += 2;
                        //스택에서 꺼내기
                        stack.pop();
                    } else {
                        stack.push(temp);
                    }
                    //인형 비교작업을 마치면 반복문을 빠져나온다.
                    break;
                }
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