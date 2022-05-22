package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//7. 이진트리 레벨탐색(BFS: Breadth-First Search) - 넓이우선탐색
class Node1 {
    int data;
    Node1 lt, rt;
    public Node1(int val) {
        data = val;
        lt = rt = null;
    }
}
public class Session07 {
    Node1 root;
    public void BFS(Node1 root) {
        Queue<Node1> queue = new LinkedList<>();
        queue.offer(root);

        int L = 0;  //노드 레벨: 몇 번만에 해당 자식노드에 접근할 수 있는지

        while (!queue.isEmpty()) {
            int len = queue.size();
            System.out.print(L + " : ");

            for (int i = 0; i < len; i++) {
                Node1 cur = queue.poll();
                System.out.print(cur.data + " ");

                //자식노드가 있는 경우에 queue에 넣어주기
                if (cur.lt != null) {
                    queue.offer(cur.lt);
                }
                if (cur.rt != null) {
                    queue.offer(cur.rt);
                }
            }
            L++; //노드 레벨 증가
            System.out.println();
            
        }
    }

    public static void main(String[] args) throws IOException {
        Session07 tree = new Session07();
        tree.root = new Node1(1);
        tree.root.lt = new Node1(2);
        tree.root.rt = new Node1(3);
        tree.root.lt.lt = new Node1(4);
        tree.root.lt.rt = new Node1(5);
        tree.root.rt.lt = new Node1(6);
        tree.root.rt.rt = new Node1(7);
        tree.BFS(tree.root);

    }
}