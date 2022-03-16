package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//5. 연속된 자연수의 합
public class Session05 {
    public static int solution(int n) {
        int answer = 0, sum = 0, lt = 1;

        for (int rt = 0; rt < n; rt++) {
            sum += rt;

            if (sum == n) {
                answer++;
                //System.out.println("rt = " + rt + ", lt = " + lt + ", sum = " + sum);
            }

            while (sum >= n) {
                sum -= lt++;

                if(sum == n) {
                    answer++;
                    //System.out.println("rt = " + rt + ", lt = " + lt + ", sum = " + sum);
                }
            }
        }

        return answer;
    }
/*
15
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().split(" ")[0]);

        System.out.println(solution(n));
    }
}
