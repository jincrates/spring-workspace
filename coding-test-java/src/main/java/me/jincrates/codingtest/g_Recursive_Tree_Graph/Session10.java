package me.jincrates.codingtest.g_Recursive_Tree_Graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//10. Tree 말단 노드까지의 가장 짧은 경로(BFS)
public class Session10 {
    Node root;
    public int BFS(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                Node curNode = queue.poll();

                //말단 노드 찾기
                if (curNode.lt == null && curNode.rt == null) {
                    return level;
                }

                if (curNode.lt != null) {
                    queue.offer(curNode.lt);
                }
                if (curNode.rt != null) {
                    queue.offer(curNode.rt);
                }
            }
            level++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        Session10 tree = new Session10();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        System.out.println(tree.BFS(tree.root));
    }
}