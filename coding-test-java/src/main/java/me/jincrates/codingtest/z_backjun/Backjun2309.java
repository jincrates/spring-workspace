package me.jincrates.codingtest.z_backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//2309.일곱 난쟁이
//https://www.acmicpc.net/problem/@@@@
public class Backjun2309 {

    public static ArrayList<Integer> solution(ArrayList<Integer> list) {
        ArrayList<Integer> answer = new ArrayList<>();
        int sum = 0;

        //내림차순으로 버블정렬
        for (int i = 0, n = list.size(); i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (list.get(j) < list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j+1, temp);
                }
            }
        }

        list.remove(0);
        for (int i = 0; i < 7; i++) {
            //System.out.println(list);
            int temp = list.get(i);
            list.remove(i);
            for (int j = 0; j < 7; j++) {
                sum += j;
            }

            if (sum == 100) {
                break;
            } else {
                list.add(temp);
            }
        }

        //오름차순
        Collections.sort(list);
        answer = list;

        return answer;
    }
/*
20
7
23
19
10
15
25
8
13
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = 9;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(Integer.parseInt(st.nextToken()));
        }

        for(int x : solution(list)) {
            System.out.println(x);
        };
    }
}


/*
public class 일곱_난쟁이_2309 {
    public static void main (String[] args) {
        일곱_난쟁이_2309 T = new 일곱_난쟁이_2309();
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        int total = 0;
        for(int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        for(int x : T.solution(arr, total)){
            //가짜난쟁이는 제외하고 출력
            if(x != 0) {
                System.out.println(x);
            }
        };
    }

    public int[] solution(int[] arr, int total) {
        boolean avail = false;
        for(int i = 0; i < 9; i++) {
            for(int j = i+1; j < 9; j++) {
                //total - 가짜난쟁이1 - 가짜난쟁이2 = 100일 경우
                if(total - arr[i] - arr[j] == 100) {
                    arr[i] = 0;
                    arr[j] = 0;
                    avail = true;
                    break;
                }
             }
            if(avail) {
                break;
            }
        }
        Arrays.sort(arr); //오름차순으로 키 정렬
        return arr;
    }
}
*/