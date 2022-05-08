package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//1966. 프린터 큐
//https://www.acmicpc.net/problem/@@@@

/*
3
1 0
5
4 2
1 2 3 4
6 0
1 1 9 1 1 1
*/
class Print {
    int id;
    int importance;  //중요도

    public Print(int id, int importance) {
        this.id = id;
        this.importance = importance;
    }
}

public class Baekjoon1966 {
    public static int solution(int total, int pageNum, int[] arr) {
        int answer = 0;
        //프린트 대기목록
        Queue<Print> queue = new LinkedList<>();

        for (int j = 0; j < total; j++) {
            queue.offer(new Print(j, arr[j]));
        }

        while (!queue.isEmpty()) {
            Print temp = queue.poll();

            for (Print x : queue) {
                //중요도 비교하여 넣을지 말지
                if (x.importance > temp.importance) {
                    queue.offer(temp);
                    temp = null;
                    break;
                }
            }
            if (temp != null) {
                answer++;

                //찾고자하는 페이지일때
                if (temp.id == pageNum) {
                    return answer;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] line1 = br.readLine().split(" ");
            int total = Integer.parseInt(line1[0]);
            int pageNum = Integer.parseInt(line1[1]);

            String[] line2 = br.readLine().split(" ");
            int[] arr = new int[line2.length];
            
            for (int j = 0, max = line2.length; j < max; j++) {
                arr[j] = Integer.parseInt(line2[j]);
            }

            System.out.println(solution(total, pageNum, arr));
        }
    }
}