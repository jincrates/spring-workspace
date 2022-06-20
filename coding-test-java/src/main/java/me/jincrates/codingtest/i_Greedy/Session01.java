package me.jincrates.codingtest.i_Greedy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//1. 씨름 선수
class Wrestler implements Comparable<Wrestler> {
    int height;
    int weight;

    Wrestler(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Wrestler o) {
        //결과값이 음수이면 오름차순, 내림차순으로 하고 싶으면 양수를 리턴(계산 순서를 바꿔주면 됨)
        return o.height - this.height;
    }
}

public class Session01 {
   public static int solution(ArrayList<Wrestler> list) {
        int answer = 0;

        //이중 for문으로는 timelimit 된다.
        //키(height)에 의해서 정렬
        Collections.sort(list);

        int maxWeight = Integer.MIN_VALUE;

        for (Wrestler w : list) {
            //System.out.println(w.height);
            if (w.weight > maxWeight) {
                maxWeight = w.weight;
                answer++;
            }
        }

        return answer;
   }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Wrestler> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Wrestler(sc.nextInt(), sc.nextInt()));
        }

        System.out.println(solution(list));

    }
}

/*
5
172 67
183 65
180 70
170 72
181 60

*/
