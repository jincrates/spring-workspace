package me.jincrates.codingtest.h_DFS_BFS;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//5. 동전교환
public class Session05 {
    static int n, m, answer = Integer.MAX_VALUE;

    public void DFS(int level, int sum, Integer[] arr) {
        //무한재귀에 빠지지 않도록 처리
        if (sum > m) {
            return;
        }
        //시간복잡도를 줄이기 위해
        if (level >= answer) {
            return;
        }

        if (sum == m) {
            //level: sum을 만드는 동전의 개수
            answer = Math.min(answer, level);
        } else {
            //n가닥으로 뻗어나간다.
            for (int i = 0; i < n; i++) {
                DFS(level + 1, sum + arr[i], arr);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Session05 main = new Session05();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        //큰 수를 먼저처리하도록 내림차순 정렬
        //해당 함수를 사용하기 위해서는 배열의 타입을 객체형으로 바꿔줘야한다.
        Arrays.sort(arr, Collections.reverseOrder());

        m = sc.nextInt();


        main.DFS(0, 0, arr);
        System.out.println(answer);
    }
}

/*
3
1 2 5
15
*/
