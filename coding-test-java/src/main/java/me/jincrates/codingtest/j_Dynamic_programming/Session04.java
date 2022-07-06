package me.jincrates.codingtest.j_Dynamic_programming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

class Brick implements Comparable<Brick>{
    public int s, h, w;
    Brick(int s, int h, int w) {
        this.s = s;
        this.h = h;
        this.w = w;
    }

    @Override
    public int compareTo(Brick o) {
        return o.s - this.s;
    }
}

// 4. 가장 높은 탑 쌓기(LIS 응용)
public class Session04 {
    static int[] dy;

    public static int solution(ArrayList<Brick> list) {
        int answer = 0;
        Collections.sort(list);
        // 탑의 높이
        dy[0] = list.get(0).h;
        answer = dy[0];

        for (int i = 1, max = list.size(); i < max; i++) {
            int max_h = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(j).w > list.get(i).w
                        && dy[j] > max_h) {
                    max_h = dy[j];
                }
            }
            dy[i] = max_h + list.get(i).h;
            answer = Math.max(answer, dy[i]);
        }


        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dy = new int[n];

        ArrayList<Brick> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int h = sc.nextInt();
            int w = sc.nextInt();
            list.add((new Brick(s, h, w)));
        }

        System.out.println(solution(list));
    }
}

/*
5
25 3 4
4 4 6
9 2 3
16 2 5
1 5 2



*/
