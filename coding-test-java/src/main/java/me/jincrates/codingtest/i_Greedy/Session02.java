package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Room implements Comparable<Room> {
    int start;
    int end;

    Room(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Room o) {
        if (this.end == o. end) {
            return this.start - o.start;
        } else {
            // 끝나는 시간으로 정렬
            return this.end - o.end;
        }
    }
}

//2. 회의실 배정
public class Session02 {
    public static int solution(ArrayList<Room> list) {
        int answer = 0;

        //끝나는 시간으로 먼저 정렬
        Collections.sort(list);

        int endTime = 0;
        for (Room r : list) {
            if (r.start >= endTime) {
                answer++;
                endTime = r.end;
            }

        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Room> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Room(sc.nextInt(), sc.nextInt()));
        }

        System.out.println(solution(list));

    }
}

/*
5
1 4
2 3
3 5
4 6
5 7

3
3 3
1 3
2 3

*/
