package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class Lecture implements Comparable<Lecture> {
    int money;
    int day;

    Lecture(int money, int day) {
        this.money = money;
        this.day = day;
    }

    @Override
    public int compareTo(Lecture ob) {
        //날짜에 대한 내림차순
        return ob.day - this.day;
    }
}

//4. 최대 수입 스케쥴(PriorityQueue 응용문제)
//각 기업은 D일 안에 와서 강연을 해주면서 M만큼 강연료를 주기로 했다.
public class Session04 {
    static int n, max = Integer.MIN_VALUE;

    public static int solution(ArrayList<Lecture> list) {
        int answer = 0;
        //Collections.reverseOrder(): 제일 큰값을 우선순위로 꺼내준다.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        Collections.sort(list);

        int j = 0;
        for (int i = max; i >= 1; i--) {
            while (j < n) {
                if (list.get(j).day < i) {
                    break;
                }
                priorityQueue.offer(list.get(j).money);
                j++;
            }

            if (!priorityQueue.isEmpty()) {
                answer += priorityQueue.poll();
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Lecture> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int d = sc.nextInt();
            list.add(new Lecture(m, d));

            if (d > max) {
                max = d;
            }
        }

        System.out.println(solution(list));
    }
}

/*
6
50 2
20 1
40 2
60 3
30 3
30 1


*/