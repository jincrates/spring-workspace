package me.jincrates.codingtest.z_backjun;

import java.util.Scanner;

//8958. OX 퀴즈
//https://www.acmicpc.net/problem/@@@@
public class Backjun8958 {

    public static void solution(int num, String[] strArr) {
        int[] answer = new int[num];
        int temp = 0;

        for (int i = 0; i < strArr.length; i++) {
            temp = 0;

            for (char c : strArr[i].toCharArray()) {
                if (c == 'O') {
                    temp++;
                } else {
                    temp = 0;
                }
                answer[i] += temp;
            }
        }

        for(int result : answer) {
            System.out.println(result);
        }
    }

//    5
//    OOXXOXXOOO
//    OOXXOOXXOO
//    OXOXOXOXOXOXOX
//    OOOOOOOOOO
//    OOOOXOOOOXOOOOX
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        String[] strArr = new String[num];
        for (int i = 0; i < num; i++){
            strArr[i] = kb.next();
        }
        solution(num, strArr);
    }
}
