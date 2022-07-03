package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//2022-07-03 데브매칭 코딩테스트 2회
// 가장 좁은 호수와 가장 넓은 호수
class Point {
    public int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class DevMatching02_03 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static int[] solution(int rows, int columns, int[][] lands) {
        int[] answer = {};
        Queue<Point> queue = new LinkedList<>();



        return answer;
    }

    public static void main(String[] args) throws IOException {
        int rows = 5;
        int colums = 6;
        int[][] lands = {{2,2}, {2,3}, {2,4}, {3,2}, {3,5}, {4,3}, {4,4}};

        System.out.println(solution(rows, colums, lands));
    }
}




/*
/*
SELECT ID                    --쿠폰의 아이디
     , CART_ID               --장바구니 아이디
     , MINIMUM_REQUIREMENT   --최소 주문 금액
     , DISCOUNT_AMOUNT       --할인 금액
FROM COUPONS

장바구니 최소 금액의 가격을 충족하지 않았는데 쿠폰이 적용된 경우


    SELECT CART_ID
     , CASE WHEN SUM_PRICE < MINIMUM_REQUIREMENT
            THEN 1 ELSE 0
                    END AS 'ABUSED'
                    FROM (
                    SELECT A.CART_ID
                    , SUM(A.PRICE) AS 'SUM_PRICE'
                    , B.MINIMUM_REQUIREMENT
                    , B.DISCOUNT_AMOUNT
                    FROM CART_PRODUCTS A
                    INNER JOIN COUPONS B ON A.CART_ID = B.CART_ID
                    GROUP BY A.CART_ID, B.MINIMUM_REQUIREMENT, B.DISCOUNT_AMOUNT
                    ORDER BY A.CART_ID
                    ) T

*/