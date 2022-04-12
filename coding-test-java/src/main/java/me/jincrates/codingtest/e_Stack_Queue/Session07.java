package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//7. 교육과정 설계
public class Session07 {
    public static String solution(String need, String plan) {
        String answer = "YES";

        Queue<Character> queue = new LinkedList<>();

        for (char x : need.toCharArray()) {
            queue.offer(x);
        }

        for (char x : plan.toCharArray()) {
            //System.out.println(queue);

            //필수 과목인지 확인
            if (queue.contains(x)) {
                //수업계획 순어과 큐 맨앞 요소 꺼내면서 필수과목 비교
                if (x != queue.poll()) {
                    return "NO";
                }
            }
        }

        //필수 과목 이수 안함
        if (!queue.isEmpty()) {
            return "NO";
        }

        return answer;
    }
/*
CBA
CDAGEB

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String need  = br.readLine();
        String plan = br.readLine();

        System.out.println(solution(need, plan));
    }
}