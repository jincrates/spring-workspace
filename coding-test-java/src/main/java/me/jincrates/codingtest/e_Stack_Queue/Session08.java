package me.jincrates.codingtest.e_Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//8. 응급실
class Patient {
    int id;
    int danger;  //위험도

    public Patient(int id, int danger) {
        this.id = id;
        this.danger = danger;
    }
}

public class Session08 {
    public static int solution(int n, int m, int[] arr) {
        int answer = 1;

        //대기목록
        Queue<Patient> waitingList = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            waitingList.add(new Patient(i, arr[i]));
        }

        while (!waitingList.isEmpty()) {
            Patient temp = waitingList.poll();

            for (Patient x : waitingList) {
                if (x.danger > temp.danger) {
                    waitingList.add(temp);
                    temp = null;
                    break;
                }
            }
            if (temp != null) {
                if (temp.id == m) {
                    return answer;
                } else {
                    answer++;
                }
            }
        }

        return answer;
    }
/*
5 2
60 50 70 80 90

6 3
70 60 90 60 60 60

*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line1  = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[] arr = new int[n];

        String[] line2  = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }
        System.out.println(solution(n, m, arr));
    }
}