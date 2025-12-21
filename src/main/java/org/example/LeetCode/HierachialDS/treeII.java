package org.example.LeetCode.HierachialDS;

import java.lang.reflect.Array;
import java.util.*;

class Pair{
    Node root;
    int x;

    public Pair(Node root,int x){
        this.root = root;
        this.x = x;
    }
}

public class treeII {




    public static Node invertTree(Node root) {
       // if(root.left!=null && root.right!=null) {
        int count =0;
        invertNodes(root,count);
            return root;
    }

    public static void invertNodes(Node root,int count){
        if (root==null) return;
        if (count%2!=0) {
            swap(root);
        }
        invertNodes(root.left,count);
        invertNodes(root.right,count);

    }
    public static Node invertTreeOdd(Node root) {
        // if(root.left!=null && root.right!=null) {
        int count =0;
        invertNodesInPairs(root.left,root.right,1);
        return root;
    }

    public static void invertNodesInPairs(Node left,Node right,int count){
        if(left==null && right==null) return;

        if (count%2!=0) {
            int temp = left.data;
            left.data = right.data;
            right.data = temp;
        }

        invertNodesInPairs(left.left,right.right,count+1);//need to pass the same count to maintain the level
        invertNodesInPairs(left.right,right.left,count+1);
    }

    public static void swap(Node node) {
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

   public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(root.data);
        addLeftNodes(root,res);
        addLeafNodes(root,res);
        addRightNodes(root,res);

        return res;

   }


   public static void addLeftNodes(Node root, ArrayList<Integer> res) {
        Node curr = root.left;
        while(curr!=null) {
            if(!isLeafNode(curr)) res.add(curr.data);
            if(curr.left!=null) curr = curr.left;
            else curr = curr.right;
        }

   }

    public static void addRightNodes(Node root, ArrayList<Integer> res) {
        ArrayList<Integer> nodes = new ArrayList<>();
        Node curr = root.right;
        while(curr!=null) {
            if(!isLeafNode(curr)) nodes.add(curr.data);
            if(curr.right!=null) curr = curr.right;
            else curr = curr.left;
        }
        for(int i= nodes.size()-1;i>=0;i--){
            res.add(nodes.get(i));
        }

    }

   public static boolean isLeafNode(Node root) {
        return (root.left==null) && (root.right==null);
   }

   public static void addLeafNodes(Node root,ArrayList<Integer> res) {
        if(isLeafNode(root)) {
            res.add(root.data);
            return;
        }
        if(root.left!=null) addLeafNodes(root.left,res);
        if(root.right!=null) addLeafNodes(root.right,res);

   }


    public static boolean isSymmetric(Node root) {

        return isValidNodes(root.left,root.right);
    }

    public static boolean isValidNodes(Node left,Node right){
        if(left==null || right==null) return (left==right);
        if(left.data!=right.data) return false;
        return isValidNodes(left.right,right.left) && isValidNodes(left.left,right.right);
    }

    public static List<Integer> topView(Node root) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        if(root==null) return res;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root,0));
        int mx=0,mn=0;
        while(!queue.isEmpty()) {

            Pair p = queue.poll();
            mx = Math.max(p.x,mx);
            mn = Math.min(p.x,mn);

            map.putIfAbsent(p.x,p.root.data); // we want the first entry only. we can see only first one of each x from top

            if(p.root.left!=null) queue.offer(new Pair(p.root.left,p.x-1));
            if(p.root.right!=null) queue.offer(new Pair(p.root.right,p.x+1));
        }

        for(int i=mn ;i<=mx;i++) {
           res.add(map.get(i));
        }

        return res;
    }

    public static ArrayList<Integer> bottomView (Node root) {
        Map<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();

        if(root==null) return res;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root,0));
        int mx=0,mn=0;
        while(!queue.isEmpty()) {

            Pair p = queue.poll();
            mx = Math.max(p.x,mx);
            mn = Math.min(p.x,mn);

            map.put(p.x,p.root.data); // we want the last entry only. we can see only last one of each x from bottom

            if(p.root.left!=null) queue.offer(new Pair(p.root.left,p.x-1));
            if(p.root.right!=null) queue.offer(new Pair(p.root.right,p.x+1));
        }

        for(int i=mn ;i<=mx;i++) {
            res.add(map.get(i));
        }

        return res;
    }


    public static List<Integer> rightSideView(Node root) {

        Queue<Node> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level = queue.size();
            for(int i=0;i<level;i++){
                Node node = queue.poll();

                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);

                /// last stored one must be right
                /// if left means need to add the first stored one
                if(i==level-1){
                    res.add(node.data);
                }


            }

        }
        return res;

    }


     public static class NodeVals{
        int y;
        int val;

        public NodeVals(int y , int val) {
            this.y = y;
            this.val = val;
        }
    }



    public static List<List<Integer>> verticalTraversalSorted(Node root) {
        Map<Integer, List<NodeVals>> map = new TreeMap<>();
        List<List<Integer>> res = new ArrayList<>();

        depthSearch(root,0,0,map);

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(int n : list) {
            List<NodeVals> nodeVals = map.get(n);

            nodeVals.sort((a,b) ->
                a.y==b.y ? Integer.compare(a.val,b.val) : Integer.compare(a.y,b.y));

            List<Integer> list1 = new ArrayList<>();
            for(NodeVals nodes : nodeVals){
                list1.add(nodes.val);
            }
            res.add(list1);
        }
        return res;


    }



    public static void depthSearch(Node root,int x,int y,Map<Integer,List<NodeVals>> map){
        if(root==null) return;

        map.putIfAbsent(x,new ArrayList<>());
        map.get(x).add(new NodeVals(y, root.data));

        depthSearch(root.left,x-1,y+1,map);
        depthSearch(root.right,x+1,y+1,map);

    }

    /// return all root to node paths
    public static List<String> rootToNode(Node root) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        pathConnect(root,res, path);
        return res;
    }

    public static void pathConnect(Node root,List<String> res,StringBuilder path) {
        if(root ==null) return;
        int len = path.length();

        path.append(root.data);
        if(isLeafNode(root)){
            res.add(path.toString());
        }
        else {
            path.append("->");
            pathConnect(root.left,res,path);
            pathConnect(root.right,res,path);
        }
        path.setLength(len); //saving length for backtracking

    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {

        return pathForNodes(root,p,q);
    }

    private static Node pathForNodes(Node root, Node p, Node q){
        if(root==null) return null;

        if(root==p || root==q) {
            return root; //might be the LCA
        }

        Node leftNode = pathForNodes(root.left,p,q);
        Node rightNode = pathForNodes(root.right,p,q);

        if(leftNode!=null && rightNode!=null) {
            return root;
        }

        return (leftNode!=null) ? leftNode : rightNode;


    }

    public static Node searchBST(Node root, int val) {
        if(root==null) return null;

        if(root.data<val){
            searchBST(root.right,val);
        }
        if(root.data>val) {
            searchBST(root.left,val);
        }
        else {
            return root;
        }

        return root;
    }

    static class Pair1{
        Node node;
        long index;

        Pair1(Node node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public static int widthOfBinaryTree(Node root) {
        Queue<Pair1> queue = new LinkedList<>();
        queue.offer(new Pair1(root,0));
        int width=0;

        while(!queue.isEmpty()){
            int size = queue.size();
            long firstIndex = queue.peek().index;
            long lastIndex = firstIndex;
            for(int i=0;i<size;i++){
                Pair1 p = queue.poll();
                long idx = p.index - firstIndex; // it'll start from 0
                lastIndex = idx;
                if(p.node.left!=null){
                    queue.offer(new Pair1(p.node.left,(2*idx)));
                }
                if(p.node.right!=null){
                    queue.offer(new Pair1(p.node.right,(2*idx+1)));
                }


            }
            width= Math.toIntExact(Math.max(width,(lastIndex - firstIndex + 1)));
        }

        return width;


    }



    public static void main(String[] args) {
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(5);
//        root.left.right = new Node(7);
//        root.right.right = new Node(4);
//        root.right.left = new Node(10);
//        root.left.left.left = new Node(6);
//        root.left.left.right = new Node(9);
//        root.right.left.left = new Node(13);
//        root.right.left.right = new Node(19);

        Node root = new Node(1);

        root.left = new Node(3);
        root.right = new Node(2);

        root.left.left = new Node(5);
        root.left.right = new Node(3);

        root.right.right = new Node(9);


        //invertTreeOdd(root);
       // System.out.println(levelOrder(root));
        //System.out.println(isSymmetric(root));
       // System.out.println((lowestCommonAncestor(root,root.right.left,root.right.left.left )).data);
        //System.out.println(topView(root));
        System.out.println(widthOfBinaryTree(root));




    }



    }
