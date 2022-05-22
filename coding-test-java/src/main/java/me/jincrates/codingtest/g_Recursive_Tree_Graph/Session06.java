package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;

//6. 부분집합 구하기(DFS)
public class Session06 {
    static int n; //집합 원소의 개수
    static int[] check; //부분집합으로 사용하는지 체크하는 배열
    public static void DFS(int L) {
        if (L == n + 1) {
            //종착점
            String temp = "";
            for (int i = 1; i <= n; i++) {
                if (check[i] == 1) {
                    temp += (i + " ");
                }
            }
            //for문 결과 출력
            if (temp.length() > 0) {
                System.out.println(temp);
            }
        } else {
            //두갈래로 뻗어나가도록
            check[L] = 1;
            DFS(L + 1);

            check[L] = 0;
            DFS(L + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        n = 3;
        check = new int[n + 1];
        DFS(1);
    }
}