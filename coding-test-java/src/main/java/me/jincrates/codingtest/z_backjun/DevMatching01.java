package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//2022-03-31 데브매칭 코딩테스트
public class DevMatching01 {

    public static int solution(int[][] dist) {
        int answer = 0;

        /*
        char[][] arr = new char [grid[0].length()][grid.length];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        
        for (int i = 0, max = grid.length; i < max; i++) {
            for (int j = 0; j < max; j++) {
                arr[i][j] = grid[i].charAt(j);

                for (int k = 0; k < 4; k++) {
                    if (arr[i][j] == arr[dx[k]][dy[k]]) {
                        answer++;
                    }
                }

            }
        }
*/

        int max = dist.length;
        int min = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < max; i++) {
            //System.out.println(dist[0][i] + " ");
            map.put(dist[0][i], i);
        }
        System.out.println(map);




        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        int[][] dist = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}};
        //String[] grid = {"??b", "abc", "cc?"};
        System.out.println(solution(dist));
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