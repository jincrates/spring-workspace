package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//4. 후위식 연산(postfix)
public class Session04 {
    public static int solution(String str) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (char x : str.toCharArray()) {
            
            //숫자일때
            if (Character.isDigit(x)) {
                stack.push(Integer.parseInt(String.valueOf(x)));
            } else {
                //여기 순서가 중요
                int rt = stack.pop();
                int lt = stack.pop();
                int cal = 0;

                switch (x) {
                    case '+': cal = lt + rt; break;
                    case '-': cal = lt - rt; break;
                    case '*': cal = lt * rt; break;
                    case '/': cal = lt / rt; break;
                    default: System.out.println("switch error");;
                }
                //연산 결과 push
                stack.push(cal);
            }
        }
        answer = stack.get(0);
        
        return answer;
    }

/*
352+*9-

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(solution(str));
    }
}