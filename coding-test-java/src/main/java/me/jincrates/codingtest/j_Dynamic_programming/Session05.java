package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// 5. 동전교환(냅색 알고리즘)
public class Session05 {
    static int n, m;
    static int[] dy;

    // dy[i] : i 금액을 만드는데 드는 최소 동전 개수
    public static int solution(int[] coin) {
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= m; j++) {
                // dy[j - coin[i]] + 1 이 부분이 핵심이다.
                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1);
            }
        }

        return dy[m];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        m = sc.nextInt();
        dy = new int[m + 1];  // 인덱스 번호가 m까지 생성되어야 하기 때문

        System.out.println(solution(arr));
    }
}

/*
3
1 2 5
15


*/
