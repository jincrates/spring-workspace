package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//6. 공주 구하기(Queue)
public class Session06 {
    public static int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(i);  //큐에 넣기
        }

        while(!queue.isEmpty()) {
            for (int i = 1; i < k; i++) {
                //앞에꺼 꺼내서 뒤에 넣어준다.
                queue.offer(queue.poll());
            }
            queue.poll();

            //마지막 요소를 담아준다.
            if (queue.size() == 1) {
                answer = queue.poll();
            }
        }

        return answer;
    }
/*
8 3

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        System.out.println(solution(n, k));
    }
}