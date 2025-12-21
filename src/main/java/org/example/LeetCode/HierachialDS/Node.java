package org.example.LeetCode.HierachialDS;

import javax.swing.*;
import java.util.*;

public class Node {

    int data;
    Node left, right;
    public Node(int val){
        data=val;
    }

}
class tree {

    public static void preOrder(Node node) {
        if(node==null) return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
    public static void postOrder(Node node) {
        if(node==null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public static void inOrder(Node node) {
        if(node==null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);

    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<Node> queue = new LinkedList<Node>(); //FIFO
        if(root==null) return res;
        int depth = 0;
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> list = new LinkedList<>();
            int levelnum = queue.size();
            for(int i=0;i<levelnum;i++) {
                if(queue.peek().left!=null) queue.offer(queue.peek().left);
                if(queue.peek().right!=null) queue.offer(queue.peek().right);
                list.add(queue.poll().data);

            }
            depth++;
            res.add(list);
        }
        System.out.println("depth.."+depth);
        return res;
    }

    public static List<Integer> iterativePreOrder(Node root) {
        List<Integer> res = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if (root==null) return res;
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if(node.right!=null) {
                stack.add(node.right);
            }
            if(node.left!=null) {
                stack.add(node.left);
            }
            res.add(node.data);
        }
        return res;
    }

    private static List<Integer> iterativeInOrder(Node root) {
        List<Integer> res = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if(root==null) return res;
        Node node = root;
        while(true){
            if(node!=null) {
                stack.push(node);  //
                node = node.left;
            }
            else {
                if(stack.isEmpty()) {
                    break;
                }
                res.add(stack.peek().data);
                node = stack.pop().right;
            }

        }
        return res;
    }

    private static List<Integer> iterativePostOrder(Node root) {
        List<Integer> result = new LinkedList<>();
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        if(root==null) return result;
        s1.push(root);
        while(!s1.isEmpty()) {
            Node node = s1.pop();
            if(node.left!=null){
                s1.push(node.left);
            }
            if(node.right!=null) {
                s1.push(node.right);
            }
            s2.push(node);

        }
        while(!s2.isEmpty()) {
            result.add(s2.pop().data);
        }

        return result;

    }

    /// Imp....
    public static int maxHeight(Node root) {
        if(root==null) return 0;
        int lh = maxHeight(root.left);
        int rh = maxHeight(root.right);
        return 1+Math.max(lh,rh); // 1 for root node
    }

    /// check if the BT is a balanced tree
    public boolean isBalanced(Node root) {
       return isValidHeight(root) != -1;
    }



    public static int isValidHeight(Node root) {

        if(root == null) return 0;
        int lh = isValidHeight(root.left);
        int rh = isValidHeight(root.right);

        if(Math.abs(lh-rh)>1) return -1;

        if(lh==-1 || rh==-1) return -1;

        return 1+Math.max(lh,rh);
    }


    /// largest left and right
    public static int diameterOfBinaryTree(Node root) {
        int[] diameter = new int[1];
        height(root,diameter);
        return diameter[0];


    }

    public static int height(Node root,int[] diameter) {
        if(root==null) return 0;
        int leftH = height(root.left,diameter);
        int rightH = height(root.right,diameter);
        diameter[0] = Math.max(diameter[0],(leftH+rightH)); /// left + right + 1 -- if nodes...left + right -- if edges
        return 1+Math.max(leftH,rightH);
    }



    /// Imp...
    public static int maxPathSum(Node root) {
        int[] maxiSum = new int[]{Integer.MIN_VALUE};
        pathSum(root,maxiSum);
        return maxiSum[0];
    }
    public static int pathSum(Node root,int[] maxiSum) {
        if(root==null) return 0;
        int leftH = pathSum(root.left,maxiSum);
        int rightH = pathSum(root.right,maxiSum);

        maxiSum[0] = Math.max(maxiSum[0],leftH+rightH+root.data); // path sum
        return root.data+Math.max(leftH,rightH); // parent gain --- if height - 1 + ---here sum root+
    }
    
    /// check if two trees are identical
    /// pre order
    /// first check the root after left then right
    public static boolean isSameTree(Node p, Node q) {

        if(p==null || q==null) {
            return (p==q);
        }
        return (p.data==q.data)&& isSameTree(p.left,q.left) && isSameTree(p.right,q.right);


    }


    /// levelorder traversal but with a flag
    public static List<List<Integer>> zigZag (Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        queue.add(root);
        boolean flag = true;
        while(!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int level = queue.size();
            for(int i=0;i<level;i++) {
                Node node = queue.poll();

                if(flag){
                    list.addLast(node.data);
                }else {
                    list.addFirst(node.data); // so that list can be reversed
                }



                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);


            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }


//    /// distance between two nodes
//    public static int distance(Node root,Node p,Node q){
//
//    }










    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(5);
        root.left.right = new Node(7);
        root.right.right = new Node(4);
        root.right.left = new Node(10);
        root.left.left.left = new Node(6);
        root.left.left.right = new Node(9);
        root.right.left.left = new Node(13);
        root.right.left.right = new Node(19);

        /// ////---------------------------------------------------------///////
//
//        Node root = new Node(0);
//        root.left = new Node(2);
//        root.right = new Node(4);
//
//        root.left.left = new Node(1);
//        root.left.right = null;
//
//        root.right.left = new Node(3);
//        root.right.right = new Node(-1);
//
//        root.left.left.left = new Node(5);
//        root.left.left.right = new Node(1);
//
//        root.right.left.left = null;
//        root.right.left.right = new Node(6);
//
//        root.right.right.right = new Node(8);

        //preOrder(root);
       // postOrder(root);
        inOrder(root);
        //System.out.println(iterativePreOrder(root));
        //System.out.println(levelOrder(root));
       // System.out.println(iterativePostOrder(root));
        //System.out.println(maxHeight(root));
        System.out.println(diameterOfBinaryTree(root));
        //System.out.println(maxPathSum(root));
        //System.out.println(zigZag(root));


    }
}
