package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//2. 괄호 문자 제거
public class Session02 {
    public static String solution(String str) {
        String answer = "";

        Stack<Character> stack = new Stack<>();

        for(char x : str.toCharArray()) {
            /*
            //1. stack에 푸쉬
            stack.push(x);

            //2. 닫는 괄호를 만났을때, 최근 여는 괄호까지 pop
            if (x == ')') {
                for (int i = stack.lastIndexOf('('), max = stack.size(); i < max; i++) {
                    stack.pop();
                }
            }
            */

            if (x == ')') {
                // 여는 괄호를 만날때까지 꺼냄
                while(stack.pop() != '(') {}
            } else {
                stack.push(x);
            }
        }

        for (int i = 0, max = stack.size(); i < max; i++) {
            answer += stack.get(i);
        }

        return answer;
    }

/*
(A(BC)D)EF(G(H)(IJ)K)LM(N)

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(solution(str));
    }
}