package org.example.LeetCode.HierachialDS;

import java.util.LinkedList;
import java.util.Queue;

public class SerialDeserialize {

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

    /// O(n) time complexity with O(n) space -- BFS - Level Order
    // Encodes a tree to a single string.
    public String serialize(treeIII.TreeNode root) {
        if(root==null) return "";

        Queue<treeIII.TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        while(!queue.isEmpty()){
            treeIII.TreeNode node = queue.poll();
            if(!sb.isEmpty()){
                sb.append(",");
            }
            if(node==null) {
                sb.append("n");
            }
            else {
                sb.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public treeIII.TreeNode deserialize(String data) {
        String[] strings = data.split(",");

        treeIII.TreeNode root = new treeIII.TreeNode(Integer.parseInt(strings[0]));

        Queue<treeIII.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i=1;
        while(!queue.isEmpty() && i<strings.length){

            treeIII.TreeNode node = queue.poll();

            if( !strings[i].equals("n")){
                treeIII.TreeNode left = new treeIII.TreeNode();
                node.left = left;
                queue.add(left);
            }
            i++;

            if(i<strings.length && !strings[i].equals("n")){
                treeIII.TreeNode right = new treeIII.TreeNode();
                node.right = right;
                queue.add(right);
            }
            i++;




        }
        return root;
    }

    /// O(n) time complexity O(1) - const space -- DFS

    public String serialize (TreeNode root) {

        StringBuilder sb = new StringBuilder();

        dfsSerialize(root,sb);

        return sb.toString();



    }

    public void dfsSerialize(TreeNode root,StringBuilder sb){

        if(root==null) {
            sb.append("n,");
        }
        sb.append(root.val).append(",");
        dfsSerialize(root.left,sb);
        dfsSerialize(root.right,sb);

    }

    public TreeNode deSerialize (String data){

        String[] parts = data.split(",");

       return dfsDeserialize(parts,0);



    }

    public TreeNode dfsDeserialize(String[] parts,int index){

        if(parts[index].equals("n")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(parts[index++]));
        root.left = dfsDeserialize(parts,index);
        root.right = dfsDeserialize(parts,index);

        return root;

    }

}
