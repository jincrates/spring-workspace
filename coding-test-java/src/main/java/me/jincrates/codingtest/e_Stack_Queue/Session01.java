package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

//1. 올바른 괄호(Stack)
public class Session01 {
    public static String solution(String str) {
        String answer = "YES";

        Stack<Character> stack = new Stack<>();

        for (char x : str.toCharArray()) {
            //여는 괄호 넣어주기
            if (x == '(') {
                stack.push(x);
            } else {
                //닫는 괄호일때 꺼내기
                if (stack.isEmpty()) {
                    return "NO";
                }
                //제일 나중에 들어온거 꺼냄
                stack.pop();
            }
        }

        //여는 괄호가 많은 상황
        if (!stack.isEmpty()) {
            return "NO";
        }

        return answer;
    }

/*
(()(()))(()
(())()
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(solution(str));
    }
}