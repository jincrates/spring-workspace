package me.jincrates.codingtest.f_Sotring_Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point> {
    public int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x == o.x) {
            return this.y - o.y;  //결과값이 음수이면 오름차순, 내림차순으로 하고 싶으면 양수를 리턴(계산 순서를 바꿔주면 됨)
        } else {
            return this.x - o.x;
        }
    }
}

//7. 좌표 정렬
public class Session07 {
/*
5
2 7
1 3
1 2
2 5
3 6

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Point> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arrayList.add(new Point(x, y));
        }

        Collections.sort(arrayList);
        
        for (Point p : arrayList) {
            System.out.println(p.x + " " + p.y);
        }
        
    }
}