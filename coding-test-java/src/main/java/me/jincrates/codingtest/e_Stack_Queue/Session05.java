package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//5. 쇠막대기
public class Session05 {
    public static int solution(String str) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0, max = str.length(); i < max; i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else {
                //레이저던 막대기던 짝꿍 괄호를 빼버림
                stack.pop();

                //레이저인지 막대기인지 판단
                if (str.charAt(i - 1) == '(') {
                    //레이저일때, 잘려진 막대기 누적
                    answer += stack.size();
                } else {
                    //막대기일 때, 막대기 끝조각 누적
                    answer++;
                }
            }
        }

        return answer;
    }

/*
(())

()(((()())(())()))(())

(((()(()()))(())()))(()())

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(solution(str));
    }
}