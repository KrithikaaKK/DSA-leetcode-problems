package org.example.LeetCode.NonLinear;

import java.util.*;

public class Graph {

    private static Map<Integer, List<Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();

    }

    public static  void addVertices (int v) {
        adjList.put(v,new LinkedList<>());
    }

    public static void addEdges (int src, int des) {
        adjList.computeIfAbsent(src,k ->new LinkedList<>()).add(des);
        adjList.computeIfAbsent(des,k ->new LinkedList<>()).add(src);
    }

    public static void dfsSearch (Set<Integer> set,int node) {

        // have to check the set if the element present

        set.add(node);
        System.out.println(node + " ");
        for(int n : adjList.get(node)){
            if(!set.contains(node)) {
                dfsSearch(set,n);
            }
        }




    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return dfs(graph,source,destination,new boolean[n]);

    }

    public boolean dfs(List<List<Integer>> graph,int src,int des,boolean[] visited){

       if(src==des) {
           return true;
       }

       visited[src] = true;

       for(int n : graph.get(src)) {
           if(!visited[n]){
               if(dfs(graph,n,des,visited)){
                   return true;
               }
           }
       }
       return false;


    }

    /// dfs - stack, bfs - queue
    public static boolean validPathIterative(int n, int[][] edges, int source, int destination) {


        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
        }
        for(int[] e : edges) {
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(source);
        while(!stack.isEmpty()) {

            int node = stack.pop();

            if (node == destination) return true;
            if (!visited[node]) {
                visited[node] = true;
                for (int num : list.get(node)) {
                    stack.push(num);
                }
            }
        }

        return false;




    }

    public static int  townJudge(int[][] trust , int n) {
        /// trust - [1,3] [2,3] [3,1] - return -1

        List<List<Integer>> list = new ArrayList<>();

        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
        }
        for(int[] num : trust) {
            list.get(num[0]).add(num[1]);
            /// since its a directed graph
        }



        return -1;
    }

    public int findCenter(int[][] edges) {

        if(edges[0][0]==edges[1][0] || edges[0][0]==edges[1][1]) {
            return edges[0][0];
        }

        return edges[0][1];

    }
 ///---------------------------------- connected components ------------------------------------------------------------------
    /// number of provinces -- connected ones - one province
    /// number of times if loop executes - no of provinces
    public int findCircleNum(int[][] isConnected) {

        int len = isConnected.length,province=0;
        boolean[] visited = new boolean[len];
        for(int i=0;i<len;i++) {
            if(!visited[i]){
                dfs(isConnected,i,visited);
                province++;
            }
        }
        return province;
    }

    public void dfs(int[][] isConn , int current, boolean[] visited) {
        visited[current] = true;

        for(int next = 0;next<isConn.length;next++) {
            if(isConn[current][next]==1&&!visited[next]){
                dfs(isConn,next,visited);
            }
        }
    }

    /// 0th room - unlocked , each room - have set of keys
    /// all rooms reacheable - true, else false
    /// BFS - iterative
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        int len = rooms.size();
        boolean[] visited = new boolean[len];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        visited[0] = true;

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(int n : rooms.get(node)){
                if(!visited[n]){
                    visited[n] = true;
                    queue.offer(n);
                }
            }

        }

        for(boolean b : visited) {
            if(!b) return false;
        }

        return true;

    }

    /// find no of islands - explore in all four direction --- no diagonal
    public int numIslands (char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) return 0;
        int count=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]=='1'){
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs (int i, int j,char[][] grid){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length
           || grid[i][j]=='0') return;

        grid[i][j] = '0';

        dfs(i - 1, j, grid); // up
        dfs(i + 1, j, grid); // down
        dfs(i, j - 1, grid); //left
        dfs(i, j + 1, grid); //right

    }


    /// surrounded regions - DFS
    /// first need to safeguard the all islands which touches the border
    /// it can also be done with reverse BFS (multi source BFS)
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        for(int i=0;i<board.length;i++) {
            if(board[i][0]=='O'){
                dfs(board,i,0);
            }
            if(board[i][n-1]=='O') {
                dfs(board,i,n-1);
            }
        }
        for(int j=0;j<board[0].length;j++) {
            if(board[0][j]=='O'){
                dfs(board,0,j);
            }
            if(board[m-1][j]=='O') {
                dfs(board,m-1,j);
            }
        }


        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if(board[i][j]=='O') board[i][j] = 'X';
                if(board[i][j]=='S') board[i][j] = 'O';
            }
        }


    }

    public static void dfs(char[][] board,int i, int j) {
        if( i<0 || j< 0 ||i>=board.length || j>=board[0].length || board[i][j]!='O'){
            return;
        }

        board[i][j] = 'S';

        dfs(board,i+1,j);
        dfs(board,i-1,j);
        dfs(board,i,j+1);
        dfs(board,i,j-1);
    }



    /// same -- count the number of 1s in a island - maxCount -> maxarea
    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) return 0;
        int maxArea=Integer.MIN_VALUE;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1){
                    int area = dfs(i,j,grid);
                    maxArea = Math.max(maxArea,area);

                }
            }
        }
        return (maxArea==Integer.MIN_VALUE) ? 0 : maxArea;




    }

    /// remove that edge so as the graph be converted to tree
    /// 
    int[] parent, rank;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n+1];
        rank = new int[n+1];

        for (int i = 1; i <= n; i++) parent[i] = i;


        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if(find(u)==find(v)) return edge;
            else {
                union(u,v);

            }
        }

        return new int[]{};


    }

    private int find(int x) {
        if(x == parent[x]) {
            return x;
        }
        int px = find(parent[x]);
        return px; // path compression
    }
    private void union(int u, int v) {
        int px = find(u), py = find(v);
        if(rank[px]<rank[py]){
            parent[px] = py;
        }else if(rank[px]>rank[py]){
            parent[py] = px;
        }
        else {
            parent[py] = px;
            rank[px]++;
        }

    }


    public static int dfs (int i, int j,int[][] grid){

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        return 1 + dfs(i+1,j,grid) + dfs( i-1, j,grid) + dfs( i, j-1,grid) + dfs(i, j+1,grid);

    }

    public int closedIsland(int[][] grid) {

        int count=0;
        for(int i=0;i<grid.length;i++) {
            if(grid[i][0]== 0){
                dfs1(i,0,grid);
            }
        }
        for(int j=0;j<grid[0].length;j++) {
            if(grid[0][j]==0){
                dfs1(0,j,grid);
            }
        }

        for(int i=0;i<grid.length;i++) {
            if(grid[i][grid[0].length-1]== 0){
                dfs1(i,grid[0].length-1,grid);
            }
        }

        for(int j=0;j<grid[0].length;j++) {
            if(grid[grid.length-1][j]==0){
                dfs1(grid.length-1,j,grid);
            }
        }

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==0){
                    dfs1(i,j,grid);
                    count++;
                }
            }
        }
        return count;



    }

    public static void dfs1 (int i,int j, int[][]grid ){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1){
            return;
        }

        grid[i][j] = 1;

        dfs1(i - 1, j, grid); // up
        dfs1(i + 1, j, grid); // down
        dfs1(i, j - 1, grid); //left
        dfs1(i, j + 1, grid); //right


    }


    /// fill up the floor with color -- starting from image[sr][sc] -- till same color exists
    /// leave other if the org color changes
    public int[][] floorFill (int[][] image , int sr, int sc, int color) {

        int org = image[sr][sc];
        if(image==null || image.length==0 || image[0].length==0) return image;

        dfs2(sr,sc,image,org,color);
        return image;

    }

    public static void dfs2 (int i, int j,int[][] grid,int org,int color){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length
                || grid[i][j]!=org) return;

        grid[i][j] = color;

        dfs2(i - 1, j, grid,org,color); // up
        dfs2(i + 1, j, grid,org,color); // down
        dfs2(i, j - 1, grid,org,color); //left
        dfs2(i, j + 1, grid,org,color); //right

    }
    static class Pair{

        int row;
        int col;
        int tm;

        public Pair(int r , int c , int t) {
            row =r;
            col =c;
            tm =t;
        }

    }


    /// multi source BFS
    public int orangesRotting(int[][] grid) {

        int rLen = grid.length;
        int cLen = grid[0].length;
        int fresh=0;
        Queue<Pair> queue = new LinkedList<>();
        int[][] visited = new int[rLen][cLen];

        for(int i=0;i<rLen;i++) {
            for(int j=0;j<cLen;j++) {
                if(grid[i][j]==2){
                    queue.offer(new Pair(i,j,0));
                    visited[i][j]=2;
                }else {
                    visited[i][j]=0;
                }
                if(grid[i][j]==1){
                    fresh++;
                }
            }
        }

        int[] rd = new int[]{-1,1,0,0};
        int[] cd = new int[]{0,0,-1,1};
        int tn =0,cnt=0;
        while(!queue.isEmpty()){

            int r = queue.peek().row;
            int c = queue.peek().col;
            int t = queue.peek().tm;
            queue.poll();
            tn = Math.max(t,tn);

            for(int i=0;i<4;i++) {
                int rx = r + rd[i];
                int cx = c + cd[i];

                if(rx>0 && cx > 0 && rx < rLen && cx < cLen && visited[rx][cx]==0 && grid[rx][cx]==1) {
                    visited[rx][cx] = 2;
                    queue.add(new Pair(rx,cx,t));
                    cnt++;

                }
            }

        }

        if(cnt!=fresh) return -1;

        return tn;



    }

    public static class Node {

        public int val;
        public List<Node> neighbors;



        public Node (int val , List<Node> neighbors) {

            this.val = val;
            this.neighbors = neighbors;
        }

    }

    /// make a deep copy(new copy of the org node) of the give node of a connected undirected graph
    /// Hashmap -- to store the new nodes , in order to connect with the new neighbors
    public static Node cloneGraph (Node node) {
        if(node == null) return node;
        Map<Node, Node> map = new HashMap<>();
        return cloneUtils(map,node);

    }

    public static Node cloneUtils (Map<Node,Node> map , Node node) {
        Node newNode = new Node(node.val, new ArrayList<>());
        List<Integer>[] graph = new ArrayList[]{new ArrayList<>()};

        map.put(node,newNode);
        for(Node node1 : node.neighbors) {
            if(!map.containsKey(node1)) {
                cloneUtils(map,node1);
            }
            else {
                newNode.neighbors.add(node1);
            }
        }
        return newNode;

    }
    /// to make connections -- add in the list  neighbors


    /// 01 Matrix -- find the distance to the nearest zero
    /// perform multi source BFS by storing all 0s in a queue
    /// and make other as 1
    public int[][] updateMatrix(int[][] mat) {

        int rLen = mat.length;
        int cLen = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<rLen;i++) {
            for(int j=0;j<cLen;j++) {
                if(mat[i][j]==0) {
                    queue.offer(new int[]{i,j});
                }
                else {
                    mat[i][j] = -1;
                }
            }
        }

        int[][] dirs = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] d : dirs){
                int newRow = curr[0] + d[0];
                int newCol = curr[1] + d[1];

                if(newRow>=0 && newRow<rLen && newCol >=0 && newCol<cLen && mat[newRow][newCol]==-1){

                    mat[newRow][newCol] = mat[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{newRow,newCol});
                }
            }

        }

        return mat;

    }

    /// 1 - water , 0 - land , --- find height -- for water -- 0
    /// Multi source BFS by taking all water initially in a queue
    /// then do BFS in all direction simultaneously
    public int[][] highestPeak(int[][] isWater) {

        int rLen = isWater.length;
        int cLen = isWater[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<rLen;i++) {
            for(int j=0;j<cLen;j++) {
                if(isWater[i][j]==1) {
                    isWater[i][j]=0;
                    queue.offer(new int[]{i,j});
                }
                else {
                    isWater[i][j] = -1;
                }
            }
        }

        int[][] dirs = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] d : dirs){
                int newRow = curr[0] + d[0];
                int newCol = curr[1] + d[1];

                if(newRow>=0 && newRow<rLen && newCol >=0 && newCol<cLen && isWater[newRow][newCol]==-1){

                    isWater[newRow][newCol] = isWater[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{newRow,newCol});
                }
            }

        }

        return isWater;

    }

    public int shortestBridge(int[][] grid) {

        int rLen = grid.length;
        int cLen = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        boolean found = false;
        ///DFS to get the 1st Island
        for(int i=0;i<rLen;i++) {
            for(int j=0;j<cLen;j++) {
                if(grid[i][j]==1) {
                    dfsBridge(i, j, queue,grid,dirs);
                    found=true;
                    break;
                }
            }
        }

        int step =0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] curr = queue.poll();
                int rx = curr[0];
                int cx = curr[1];

                for (int[] d : dirs) {

                    int r = rx + d[0];
                    int c = cx + d[1];

                    if (r<0 || c<0 || r>= grid.length || c>=grid[0].length||  grid[r][c] == 2) {
                        continue; // skip the invalid conditions
                    }
                    if (grid[r][c] == 1) return step;

                    grid[r][c] = 2; // mark as visited 0 -> 2
                    queue.offer(new int[]{r, c});

                }
            }
            step++; // record the count once the full level completed

        }
        return step;

    }

    public void dfsBridge(int r, int c , Queue<int[]> queue , int[][] grid,int[][] dirs) {
        if(r<0 || c<0 || r>=grid.length || r>=grid[0].length || grid[r][c]!=1) {
            return;
        }
        grid[r][c] = 2;
        queue.offer(new int[]{r,c});
        for(int[] d : dirs) {
            dfsBridge(r + d[0],c + d[1],queue,grid,dirs);
        }

    }



        public static void main(String[] args) {
        Graph graph = new Graph();
        addVertices(2);
        addVertices(1);
        addVertices(3);
        addVertices(0);

        addEdges(2,10);
        addEdges(3,10);
        addEdges(10,1);
        addEdges(3,1);

        dfsSearch(new HashSet<>(),2);

    }
}
