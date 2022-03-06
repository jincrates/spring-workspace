package me.jincrates.codingtest.b_Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//8. 등수 구하기
public class Session08 {
    public static int[] solution(int n, int[] arr) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            list.add(arr[i]);
        }

        //내림차순 정렬
        list.sort(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i] == list.get(j)) {
                    answer[i] = j + 1; //인덱스이기 때문에 1을 더해줌
                    break;
                }
            }
        }

        return answer;
    }

/*
5
87 89 92 100 76
 */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }

        for (int result : solution(n, arr)) {
            System.out.print(result + " ");
        }
    }
}
