package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//9. Tree 말단 노드까지의 가장 짧은 경로(DFS)
//최단경로는 BFS로 풀어야 한다.
//DFS로 풀기 위해서는 자식 노드 양쪽이 온전하게 있어야함
public class Session09 {
    Node root;
    public int DFS(int level, Node root) {
        //말단 노드의 level을 리턴
        if (root.lt == null && root.rt == null) {
            return level;
        } else {
            return Math.min(DFS(level + 1, root.lt), DFS(level + 1, root.rt));
        }
    }

    public static void main(String[] args) throws IOException {
        Session09 tree = new Session09();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);

        System.out.println(tree.DFS(0, tree.root));
    }
}