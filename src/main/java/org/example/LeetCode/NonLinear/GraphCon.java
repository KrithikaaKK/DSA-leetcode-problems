package org.example.LeetCode.NonLinear;

import java.util.ArrayList;
import java.util.List;

public class GraphCon {

    /// Map<Integer,List<Integer>> 
    private static List<List<Integer>> list;

    public GraphCon(int vertices) {

        list =  new ArrayList<>();
        for(int i=0;i<=vertices;i++) {
            list.add(new ArrayList<>());
        }
    }
    /// undirected graph
    public static void addEdges (int src,int des) {
        list.get(src).add(des);
        list.get(des).add(src);
    }



    public static void dfs(int node,boolean[] visited,List<List<Integer>> adjList) {
        visited[node] = true;
        System.out.print(" " + node);
        for(int adjEle : adjList.get(node)){
           if(!visited[adjEle]) {
               dfs(adjEle,visited,adjList);
           }
        }


    }

    public static void main(String[] args){
        GraphCon graphCon = new GraphCon(5);
        addEdges(2,3);
        addEdges(3,4);
        addEdges(1,4);
        addEdges(1,2);

        dfs(1,new boolean[6],list);

    }


}
