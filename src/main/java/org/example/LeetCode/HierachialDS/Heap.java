package org.example.LeetCode.HierachialDS;

import org.example.LeetCode.ListNode;

import java.util.*;



///---------------------- K <----> Heapss--------------------------------
public class Heap {

    private MinHeap minHeap;

    private int k;

    public Heap(int k, int[] nums) {


        this.k = k;
        minHeap = new MinHeap();
        for(int n : nums){
            add(n);
        }

    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size()>k){
            minHeap.remove();
        }
        return minHeap.peek(); // root is the smallest of the k's -- desired
    }

    public static int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int n : stones){
            maxHeap.offer(n);
        }

        while(maxHeap.size()>1){
            /// as we need to select 2 heaviest ones
            int n1 = maxHeap.poll();
            int n2 = maxHeap.poll();

            if(n1!=n2){
                maxHeap.offer(n1-n2);
            }
        }
        return (!maxHeap.isEmpty()) ? maxHeap.peek() : 0;


    }

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for(int n : nums){
            if(minHeap.size()<k){
                minHeap.offer(n);
            }else if (minHeap.peek()<=n){
                minHeap.offer(n);
                if(minHeap.size()>k){
                    minHeap.poll();
                }
            }
        }
        return minHeap.peek();


    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[k];
        for(int n : nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> minheap = new PriorityQueue<>((a,b)-> a.getValue() - b.getValue());

        for(Map.Entry<Integer,Integer> m : map.entrySet()){
            if(minheap.size()<k){
                minheap.offer(m);
            }else if(minheap.peek().getValue() < m.getValue() ){
               minheap.offer(m);
               if(minheap.size()>k) {
                   minheap.poll();
               }
            }
        }
        int c = 0;
        while(!minheap.isEmpty()) {
            result[c] = minheap.poll().getKey();
            c++;
        }

        return result;

        /// adc --- abc -- abc , adc



    }


    public static int[][] kClosest(int[][] points, int k) {

        double[] result = new double[points.length];

        for(int i=0;i<points.length;i++){
            int n = points[i][0];
            int m = points[i][1];


            double res =  Math.sqrt((n*n)+(m*m));
            result[i] = res;
        }
        System.out.println(Arrays.toString(result));

        Arrays.sort(result);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for(double n : result){
            if(minHeap.size()<k){
               // minHeap.offer(n);
            }else if(minHeap.peek()<n){

            }
        }


        /// 20 - [2,-2] -- need


        /// smallest distance -- min or max

        return null;
    }

    static class Pair {

        String s;
        int k;

       public Pair(String s,int k){
            this.s =s;
            this.k = k;

        }
    }

    public static List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> map = new HashMap<>();
        List<String> result =  new ArrayList<>();
        for(String s : words){
            map.put(s,map.getOrDefault(s,0)+1);
        }

        PriorityQueue<Map.Entry<String,Integer>> minheap = new PriorityQueue<>(
                (a,b)-> {
                    if(!a.getValue().equals(b.getValue())){
                        return a.getValue() - b.getValue();
                    }
                    return b.getKey().compareTo(a.getKey()); /// maintain the lexio..largest and as well as lowest freq -- to top
                });

        for(Map.Entry<String,Integer> m : map.entrySet()) {
            if(minheap.size()<k) {
                minheap.offer(m);
            }else {
                minheap.poll();

            }
        }

        while(!minheap.isEmpty()) {
            result.add(minheap.poll().getKey());
        }

        ///  as the result will be in order of lexicographically desc (larger one)
        Collections.sort(result);
        return result;




    }

    public static String frequencySort(String s) {

        Map<Character,Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a,b) -> (b.getValue() - a.getValue()));

        for(Map.Entry<Character,Integer> m : map.entrySet()) {
            maxHeap.offer(m);
        }
        StringBuilder sb = new StringBuilder();

        while(!maxHeap.isEmpty()){
            Map.Entry<Character,Integer> m = maxHeap.poll();
            sb.append(String.valueOf(m.getKey()).repeat(Math.max(0, m.getValue())));

        }

        return sb.toString();
    }

    /// no two same characters are adjacent
    public static String reorganizeString(String s) {

        Map<Character,Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

        for(char c : s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);

            if(map.get(c) > (s.length()+1)/2){
                return "";
            }
        }

        maxHeap.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        Map.Entry<Character,Integer> prev = null;
        while(!maxHeap.isEmpty()) {

            Map.Entry<Character,Integer> curr = maxHeap.poll();

            sb.append(curr.getKey());
            curr.setValue(curr.getValue()-1);

            if(prev!=null && prev.getValue()>0) {
                maxHeap.offer(prev);
            }

            prev = curr;

        }

        return sb.toString();




    }

    /// return k pairs of smallest sum
     static List<List<Integer>> pairSum(int[] nums1,int[] nums2,int k) {
        /// [1,2,3] [7,8,9] k=2-- > [1,7],[1,8] -- i/ps were sorted

         PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]); ///order by sum (asc)

         List<List<Integer>> list = new ArrayList<>();

         if(nums1.length==0 || nums2.length==0 || k==0) return list;

         for(int i=0;i<k;i++) {
             minHeap.offer(new int[]{nums1[i]+nums2[0],i,0});
         }

         while(!minHeap.isEmpty() && list.size()<k) {

             int[] curr  = minHeap.poll();

             int i = curr[1];
             int j = curr[2];

             list.add(List.of(nums1[i],nums2[j]));

             if(j+1<nums2.length) {
                 /// while after popping a entry, another one is added to avoid duplicates
                 minHeap.offer(new int[]{nums1[i]+nums2[j+1],i,j+1});
             }

         }



         return list;

     }

     /// merge K linked list -- with heap(minheap), we decide the smallest one to choose
     public ListNode mergeKLists(ListNode[] lists) {
         ListNode dummy = new ListNode(-1);
         ListNode curr = dummy;
         PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val,b.val));

         for(ListNode listNode : lists) {
             minHeap.offer(listNode);
         }

         while(!minHeap.isEmpty()) {
             ListNode listNode = minHeap.poll();

             curr.next = listNode;

             if(listNode.next!=null){
                 minHeap.offer(listNode.next);
             }
             curr = listNode;
         }
         return dummy.next;

     }



    public static void main (String[] args) {
        //System.out.println(lastStoneWeight(new int[]{2,7,8,4,1,1,1}));
        //System.out.println(Arrays.toString(topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2)));
//        System.out.println(kClosest(new int[][]{
//
//                {1,3},
//                {-2,2}},2));
       // System.out.println(spiralOrder());
        //System.out.println(topKFrequent(new String[]{"set","set","i","i","leetcode","this","i","this"},3));
        //System.out.println(frequencySort("abbbcc"));
        //System.out.println(reorganizeString("aaab"));
        System.out.println(pairSum(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 2));
    }



}

class MinHeap{

    private List<Integer> heap = new ArrayList<>();

    public int size(){
        return heap.size();
    }

    public int peek(){
        return heap.get(0);
    }

    public void add(int n) {
        heap.add(n);
        upheap(heap.size()-1);
    }

    public int remove(){
        int root = heap.get(0);
        int last = heap.remove(heap.size()-1);

        if(!heap.isEmpty()){
            heap.set(0,last);
            downheap(0);

        }
        return root;
    }

    public void upheap(int i){
        while(i>0){
            int parent = (i-1)/2;
            if(heap.get(i)>=heap.get(parent)) break;
            swap(parent,i);
            i = parent;
        }
    }

    public void swap ( int i, int j){
        int temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }

    public void downheap(int i) {
        int min = i;
        int left = (2*i) + 1;
        int right = (2*i) + 2;


            if(left<heap.size() && heap.get(left)>heap.get(min)){
                min = left;
            }
            if(right<heap.size() && heap.get(right)>heap.get(min)){
                min = right;
            }
            if(min!=i){

            }
            swap(min,i);
            i = min;


    }


}


