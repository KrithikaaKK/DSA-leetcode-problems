package org.example.LeetCode.Design;


import org.example.LeetCode.PrefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

    public static class Pair {

        Integer time;
        String value;

        public Pair(Integer time,String value){
            this.time = time;
            this.value = value;
        }

    }

    Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();

    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key,k -> new ArrayList<>()).add(new Pair(timestamp,value));
    }

    public String get(String key, int timestamp) {

        if(!map.containsKey(key)){
            return "";
        }else {

            List<Pair> list = map.get(key);

            int low  = 0;
            int high = list.size()-1;
            String result="";
            while(low<=high){
                int mid = low + (high-low)/2;

                if(list.get(mid).time <= timestamp) {
                    result = list.get(mid).value;
                    low = mid +1;
                }else{
                    high = mid -1;
                }
            }
            return result;

        }


    }
}
