package org.example.LeetCode.HierachialDS;

public class BST {

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



    public static void main(String[] args) {

        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        root.left.left.left = new Node(10);
        root.left.left.right = new Node(25);

        root.right.left.left = new Node(55);
        root.right.left.right = new Node(65);

       // System.out.println(lowestCommonAncestor(root,root.right.right,root.right ).data);

        System.out.println(searchBST(root,60).data);


    }
}
