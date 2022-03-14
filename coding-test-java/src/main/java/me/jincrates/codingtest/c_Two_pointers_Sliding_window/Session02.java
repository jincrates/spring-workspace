package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

//2. 공통원소 구하기
public class Session02 {
    public static ArrayList<Integer> solution(int[] arr1, int[] arr2) {
        ArrayList<Integer> answer = new ArrayList<>();

        //***배열 오름차순 정렬
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int p1 = 0, p2 = 0;

        //둘 중 하나라도 소진되면 false
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] == arr2[p2]) {
                answer.add(arr1[p1]);
                p1++;
                p2++;
            } else if (arr1[p1] > arr2[p2]) {
                p2++;
            } else {
                p1++;
            }
        }

        return answer;
    }
/*
5
1 3 9 5 2
5
3 2 5 7 8

*/
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        //첫 번째 배열
        int size1 = kb.nextInt();
        int[] arr1 = new int[size1];

        for (int i = 0; i < size1; i++) {
            arr1[i] = kb.nextInt();
        }

        //두 번째 배열
        int size2 = kb.nextInt();
        int[] arr2 = new int[size2];

        for (int i = 0; i < size2; i++) {
            arr2[i] = kb.nextInt();
        }

        for (int result : solution(arr1, arr2)) {
            System.out.print(result + " ");
        }
    }
}
