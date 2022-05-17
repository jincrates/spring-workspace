package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.Scanner;

//5. 이진트리순회(DFS: 깊이우선 탐색)
/*
전위순회: 부 - 왼 - 오  : 1 2 4 5 3 6 7
중위순회: 왼 - 부 - 오  : 4 2 5 1 6 3 7
후위순회: 왼 - 오 - 부  : 4 5 2 6 7 3 1
*/
class Node {
    int data;
    Node lt, rt;
    public Node(int val) {
        data = val;
        lt = rt = null;
    }
}
public class Session05 {
    Node root;
    public static void DFS(Node root) {
        if(root == null) {
            return;
        } else {
            //여기다 쓰면 전위순회
            //System.out.print(root.data + " ");
            DFS(root.lt);
            //여기다 쓰면 중위순회
            //System.out.print(root.data + " ");
            DFS(root.rt);
            //여기다 쓰면 후위순회
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //int n = sc.nextInt();
        Session05 tree = new Session05();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        DFS(tree.root);

    }
}