package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//2164. 카드2
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon2164 {

    public static int solution(int n) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(i);  //큐에 넣기
        }

        while (!queue.isEmpty()) {
            //마지막 요소를 담아준다.
            if (queue.size() == 1) {
                answer = queue.poll();
                break;
            }

            //카드버리기
            queue.poll();

            //다음카드 제일 뒤로 보내기
            queue.offer(queue.poll());
        }

        return answer;
    }
/*
6 => 4
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(solution(n));
    }
}