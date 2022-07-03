package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//2022-07-03 데브매칭 코딩테스트 2회
public class DevMatching02_02 {

    public int[][] solution(int n, boolean horizontal) {
        int[][] answer = {};

        int[][] room = new int[n][n];

        int order = 1;
        room[0][0] = order;

        if (horizontal) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    room[i][j] = order++;
                }
            }
        }
        answer = room;

        return answer;
    }

    public static void main(String[] args) throws IOException {
        DevMatching02_02 main = new DevMatching02_02();
        // case 1
        int n = 4;
        boolean horizontal = true;

        System.out.println(Arrays.deepToString(main.solution(n, horizontal)));
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