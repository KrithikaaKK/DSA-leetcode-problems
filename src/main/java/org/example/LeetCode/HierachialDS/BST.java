package org.example.LeetCode.HierachialDS;

import java.util.ArrayList;
import java.util.List;

public class BST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {

        if(root == null) return null;

        if(p.data>root.data && q.data> root.data) {
            return lowestCommonAncestor(root.right,p,q);
        }
        else if(p.data< root.data && q.data<root.data) {
            return lowestCommonAncestor(root.left,p,q);
        }
        else {
            return root;
        }
    }

    public static Node searchBST(Node root, int val) {
        if(root==null) return null;




        if(root.data==val){
            return root;
        }
        if(root.data<val){
            return searchBST(root.right,val);
        }
        else {
            return searchBST(root.left,val);

        }

    }


    public static int kthSmallest(Node root, int k) {

        int[] count = new int[1];
        int[] res = new int[1];

        smallest(root,count,k,res);

        return res[0];

    }


    public static void smallest(Node root,int[] count,int k,int[] res){
        if(root==null) return;
        smallest(root.left,count,k,res);
        count[0]++;
        if(count[0]==k){
            res[0] = root.data;
        }
        smallest(root.right,count,k,res);


    }

    public static int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root,list);

        int left =0 ,right=1;
        int diff = Integer.MAX_VALUE;
        while(left<list.size() && right<list.size()){
           int dif = list.get(right) - list.get(left);
            diff = Math.min(dif,diff);
            left++;
            right++;
        }
        return diff;

    }

    public static void inOrder(TreeNode root,List<Integer> list) {
        if(root==null) return;
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);

    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        inOrder(root,list);

        for(int i=1;i<list.size();i++){
            if(list.get(i-1).compareTo(list.get(i))>0){
                return false;
            }
        }
        return true;
    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {

        if(root==null) return new TreeNode(val);

            if(root.val<val){
                root.right=insertIntoBST(root.right,val);
            }else {
                root.left = insertIntoBST(root.left,val);
            }
            return root;
    }




    public static void main(String[] args) {

//        Node root = new Node(50);
//
//        root.left = new Node(30);
//        root.right = new Node(70);
//
//        root.left.left = new Node(20);
//        root.left.right = new Node(40);
//
//        root.right.left = new Node(60);
//        root.right.right = new Node(80);
//
//        root.left.left.left = new Node(10);
//        root.left.left.right = new Node(25);
//
//        root.right.left.left = new Node(55);
//        root.right.left.right = new Node(65);

       // System.out.println(lowestCommonAncestor(root,root.right.right,root.right ).data);

       // System.out.println(searchBST(root,60).data);

       // System.out.println(kthSmallest(root,2));

        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(insertIntoBST(root,5));


        //System.out.println(getMinimumDifference(root));

    }
}
