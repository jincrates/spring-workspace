package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time>{
    int time;
    char status;

    Time(int time, char status) {
        this.time = time;
        this.status = status;
    }

    @Override
    public int compareTo(Time ob) {
        if (this.time == ob.time) {
            return this.status - ob.status;
        } else {
            return this.time - ob.time;
        }
    }
}

//3. 결혼식
public class Session03 {
    public static int solution(ArrayList<Time> list) {
        int answer = Integer.MIN_VALUE;
        Collections.sort(list);

        int cnt = 0;
        for (Time t : list) {
            //System.out.println(t.time + " " + t.status);
            cnt = (t.status == 's') ? cnt + 1 : cnt - 1;
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Time> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Time(sc.nextInt(), 's')); //시작시간
            list.add(new Time(sc.nextInt(), 'e')); //종료시간
        }

        System.out.println(solution(list));
    }
}

/*
5
14 18
12 15
15 20
20 30
5 14

*/