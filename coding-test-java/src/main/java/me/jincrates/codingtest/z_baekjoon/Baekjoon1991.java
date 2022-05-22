package me.jincrates.codingtest.z_baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//1991. 트리순회
//https://www.acmicpc.net/problem/@@@@
public class Baekjoon1991 {
    Node root;
    public void createNode(char data, char left, char right) {
        if (root == null) {
            root = new Node(data);

            //좌우 값이 있는 경우에만 노드 생성
            if (left != '.') {
                root.lt = new Node(left);
            }
            if (right != '.') {
                root.rt = new Node(right);
            }
        } else {
            //초기 상태가 아니면 어디에 들어가야하는지 찾아야함
            searchNode(root, data, left, right);
        }
    }

    public void searchNode(Node root, char data, char left, char right) {
        //도착한 노드가 nulld이면 죄귀 종료
        if (root == null) {
            return;
        //들어갈 위치를 찾아다면 좌우 노드 추가
        } else if(root.data == data) {
            if (left != '.') {
                root.lt = new Node(left);
            }
            if (right != '.') {
                root.rt = new Node(right);
            }
        } else {
            searchNode(root.lt, data, left, right); //왼쪽 재귀 탐색 
            searchNode(root.rt, data, left, right); //오른쪽 재귀 탐색
        }
    }

    // 전위순회 : 루트 -> 좌 -> 우
    public static void preorder(Node root) {
        System.out.print(root.data); //먼저 가운데 출력
        if(root.lt != null) {
            preorder(root.lt); //그 다음 왼쪽
        }
        if(root.rt != null) {
            preorder(root.rt); //마지막 오른쪽
        }
    }
    // 중위순회 : 좌 -> 루트 -> 우
    public static void inorder(Node root) {
        if(root.lt != null) {
            preorder(root.lt); //그 다음 왼쪽
        }
        System.out.print(root.data); //먼저 가운데 출력
        if(root.rt != null) {
            preorder(root.rt); //마지막 오른쪽
        }
    }
    // 후위순회 : 좌 -> 우 -> 루트
    public static void postorder(Node root) {
        if(root.lt != null) {
            preorder(root.lt); //그 다음 왼쪽
        }
        if(root.rt != null) {
            preorder(root.rt); //마지막 오른쪽
        }
        System.out.print(root.data); //먼저 가운데 출력
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Baekjoon1991 tree = new Baekjoon1991();

        for (int  i = 0; i < n; i++) {
            char[] data;
            data = br.readLine().replaceAll(" ", "").toCharArray();
            tree.createNode(data[0], data[1], data[2]);
        }

        //전위 순회
        tree.preorder(tree.root);
        System.out.println();

        //중위 순회
        tree.inorder(tree.root);
        System.out.println();

        //후위 순회
        tree.postorder(tree.root);

        br.close();
    }
}

class Node {
    char data;
    Node lt, rt;
    public Node(char val) {
        this.data = val;
        lt = rt = null;
    }
}

/*
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .

*/