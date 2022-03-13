package me.jincrates.codingtest.c_Two_pointers_Sliding_window;

import java.util.ArrayList;
import java.util.Scanner;

//1. 두 배열 합치기
// 단순 반복문을 사용하면 시간복잡도가 엄청나게 증가함
public class Session01 {
    public static ArrayList<Integer> solution(int[] arr1, int[] arr2) {
        ArrayList<Integer> answer = new ArrayList<>();
        int p1 = 0, p2 = 0;

        //둘 중 하나라도 소진되면 false
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] < arr2[p2]) {
                answer.add(arr1[p1++]);
            } else {
                answer.add(arr2[p2++]);
            }
        }

        //남아있는 요소 추가 - 뭐가 남았는지 모르기 때문에 둘 다 작성
        while (p1 < arr1.length) {
            answer.add(arr1[p1++]);
        }
        while (p2 < arr2.length) {
            answer.add(arr2[p2++]);
        }

        return answer;
    }
/*
3
1 3 5
5
2 3 6 7 9

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
