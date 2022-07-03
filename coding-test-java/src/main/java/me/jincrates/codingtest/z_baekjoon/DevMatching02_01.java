package me.jincrates.codingtest.z_baekjoon;

import java.io.IOException;
import java.util.HashMap;

//2022-07-03 데브매칭 코딩테스트 2회
public class DevMatching02_01 {
    public static int solution(int[] grade) {
        int answer = 0;

        while (true) {
            int ch = 0;
            for (int i = 1, max = grade.length; i < max; i++) {
                if (grade[i - 1] > grade[i]) {
                    grade[i - 1] = grade[i - 1] - 1;
                    answer += 1;
                    ch = 1;
                }
            }

            if (ch == 0) {
                break;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        // 점수 순서
        //int[] grade = {2, 1, 3};
        //int[] grade = {1, 2, 3};
        int[] grade = {3, 2, 3, 6, 4, 5};

        System.out.println(solution(grade));
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