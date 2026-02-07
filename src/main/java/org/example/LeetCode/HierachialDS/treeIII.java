package org.example.LeetCode.HierachialDS;

import java.util.*;

public class treeIII {

     static int preIndex = 0;
    static int postIndex = 0;
     static Map<Integer,Integer> map = new HashMap<>();


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



    public static boolean hasPathSum(TreeNode root, int targetSum) {
        int sum  = 0;


        return rootToNode(root,sum,targetSum);


    }

    /// return true if the target sum equals any of the rootToNode sum
    public static boolean rootToNode(TreeNode root,int sum,int target){
        if(root==null) return false;

        sum += root.val;

        if(isLeafNode(root)) {
            return (sum == target);
        }

        boolean left = rootToNode(root.left,sum,target);
        boolean right= rootToNode(root.right,sum,target);


        return left | right;

    }
    public static boolean isLeafNode(TreeNode root) {
        return (root.left==null) && (root.right==null);
    }


    /// return the path whose sum equals target ( root to node)
    public static List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> ls = new LinkedList<>();
        int sum=0;
        rootToNode(root,res,ls,sum,targetSum);
        return res;

    }

    public static void rootToNode(TreeNode root,List<List<Integer>> res,List<Integer> ls,int sum,int target){
        if(root==null) return;

        System.out.println(root.val + "..." + sum);

        ls.add(root.val);
        sum += root.val;

        if(isLeafNode(root)){
            if(sum==target){
                res.add(ls);
            }
        }
        else {
            rootToNode(root.left,res,ls,sum,target);
            rootToNode(root.right,res,ls,sum,target);
        }

        ls.remove(ls.size()-1);


    }

    /// return no of path whose sum equals target (any path)
    public static int pathSum(TreeNode root, int targetSum) {
        HashMap<Integer,Integer> map = new HashMap<>();
        return anyPath(root,0,targetSum,map);
    }

    public static int anyPath(TreeNode root,int currSum,int target,HashMap<Integer,Integer> map) {
        if(root==null) return 0;

        currSum += root.val;
        int path = map.getOrDefault(currSum - target,0);

        map.put(currSum ,map.getOrDefault(currSum,0)+1);

        path += anyPath(root.left,currSum,target,map);
        path += anyPath(root.right,currSum,target,map);

        map.put(currSum,map.get(currSum)-1);

        return path;


    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return construct(preorder,0,inorder.length-1);
    }

    public static TreeNode construct(int[] preorder,int inStart,int inEnd){

        if(inStart>inEnd) return null;

        TreeNode node = new TreeNode(preorder[preIndex++]);

        int mid = map.get(node.val);

        // split the array to leaf and right with root in mid
        node.left = construct(preorder,inStart,mid-1);
        node.right = construct(preorder,mid+1,inEnd);

        return node;


    }

    public static TreeNode buildTreePost(int[] inorder, int[] postorder) {
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        postIndex = postorder.length-1;
        return construct(postorder,0,inorder.length-1);

    }

    public static TreeNode constructPost (int[] postOrder,int inStart,int inEnd){

        if(inStart>inEnd) return null;

        TreeNode root = new TreeNode(postOrder[postIndex--]);

        int mid = map.get(root.val);

        root.right = constructPost(postOrder,mid+1,inEnd);
        root.left = constructPost(postOrder,inStart,mid-1);

        return root;

    }

    public void flatten(TreeNode root) {
        TreeNode root1 = root;

        root =  helper(root);

    }

    public static TreeNode helper(TreeNode root2){
        if(root2==null) return null;



        TreeNode root = new TreeNode(root2.val);


        root.right = helper(root2.left);

        return root;

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if(p==null || q==null) return (p==q);
        return (p.val==q.val)&& isSameTree(p.left,q.left) && isSameTree(p.right,q.right);


    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {



        if((root.val== subRoot.val)&&isSameTree(root,subRoot)) {
            return true;
        }


        boolean left =  isSubtree(root.left, subRoot);
        boolean right =  isSubtree(root.right, subRoot);
        ///}
        return (left || right);


    }








    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10);
//
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(-3);
//
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//
//        root.right.right = new TreeNode(11);
//
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//
//        root.left.right.right = new TreeNode(1);


//        TreeNode root = new TreeNode(1);
//
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(2);
//
//        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(3);
//
//         root.right.right = new TreeNode(9);

       // System.out.println(hasPathSum(root,9));
        //System.out.println(pathSumII(root,9));
        //System.out.println(pathSum(root,8));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
       // root.right = new TreeNode(3);

        TreeNode subRoot = new TreeNode(1);

        System.out.println(isSubtree(root,subRoot));

// Expected: false

        buildTree(new int[]{3, 9, 20, 15, 7},new int[]{9, 3, 15, 20, 7});
    }
}
